import React, { Component } from 'react';
import { withRouter } from 'react-router-dom'
import { restService } from '../Service/RESTService'
import Modal from '../Modal/Modal'

class Book extends Component {

    constructor(props) {
        super(props)
        this.setLibraryId();
    }

    setLibraryId = () => {
        if (this.props.location.state) {
            localStorage.setItem("libraryId",
                JSON.stringify(this.props.location.state.libraryId));
        }
    }

    state = {
        books: [],
        book: {
            bookId: "",
            bookName: "",
            bookAuthor: "",
            libraryId: localStorage.getItem("libraryId"),
            bookIsbn: ""
        },
        modal: {
            action: "",
            show: false
        }
    }

    getLibraryId = () => {
        return localStorage.getItem("libraryId");
    }

    openModalHandler = (action) => {
        this.setState({
            modal: {
                action: action,
                show: true
            }
        })
    }

    closeModalHandler = () => {
        this.setState({
            modal: {
                action: "",
                show: false
            },
            book: {
                bookName: "",
                bookAuthor: "",
                bookIsbn: ""
            }
        })
    }

    changeHandler = (e) => {
        let book = Object.assign({}, this.state.book);
        book[e.target.id] = e.target.value;
        this.setState({ book: book })
    }

    onOkButtonClickHandler = (action) => {

        let book = Object.assign({}, this.state.book);
        book["libraryId"] = this.getLibraryId();
        book["action"] = action;

        restService.addBook(book).then(response => {
            if (response.status < 400) {
                this.closeModalHandler();
                localStorage.setItem("libraryId", "");
                this.props.history.push("/");
            }
        });
    }

    onUpdateClickHandler = (action, bookId) => {
        let book = this.state.books.filter(book => book.bookId === bookId)[0];
        this.setState({
            book: book
        })
        this.openModalHandler(action);

    }

    getAllBooks = () => {
        restService.getBooksByLibrary(this.getLibraryId()).then(data => {
            this.setState({
                books: data
            })
        })
            .catch(error => {
            });
    }


    componentDidMount() {
        this.getAllBooks();
    }

    render() {
        return (
            <>
                <h3>Library Application</h3>
                <div className="panel panel-default">
                    <div className="panel-heading">Add Books</div>
                    <div className="panel-body">
                        <div className="panel panel-default table-fixed">
                            <table className="table">
                                <thead>
                                    <tr>
                                        <th className="col-width-130">S. No</th>
                                        <th>Book Name</th>
                                        <th>Author</th>
                                        <th>ISBN</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    {
                                        this.state.books.map((book, index) => {
                                            return (
                                                <tr key={"tableRow" + index}>
                                                    <td>{index + 1}</td>
                                                    <td>{book.bookName}</td>
                                                    <td>{book.bookAuthor}</td>
                                                    <td>{book.bookIsbn}</td>
                                                    <td> <button type="button" className="btn btn-small btn-default"
                                                        onClick={this.onUpdateClickHandler.bind(this, "update", book.bookId)}>Update</button></td>
                                                </tr>
                                            )

                                        })}
                                </tbody>
                            </table>
                        </div>
                    </div>
                    <div className="panel-footer">
                        <div className="display-inline">
                            <button type="button" className="btn btn-default" onClick={this.openModalHandler.bind(this, "actionAdd")}>Add</button>
                        </div>
                    </div>
                    <Modal title={this.state.modal.action === "actionAdd" ? "Add Book" : "Update Book"}
                        show={this.state.modal.show}
                        handleClose={this.closeModalHandler}
                        handleOk={this.onOkButtonClickHandler.bind(this, this.state.modal.action)}
                        labelOk={this.state.modal.action === "actionAdd" ? "Add" : "Update"}
                        labelClose="Exit">
                        <div className="row">
                            <div className="col-lg-4">
                                Book Name:
                            </div>
                            <div className="col-lg-4">
                                <input type="text"
                                    className="form-control"
                                    id="bookName"
                                    value={this.state.book.bookName}
                                    onChange={this.changeHandler} />
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-lg-4">
                                Author:
                            </div>
                            <div className="col-lg-4">
                                <input type="text" className="form-control"
                                    id="bookAuthor" value={this.state.book.bookAuthor} onChange={this.changeHandler} />
                            </div>
                        </div>
                        <div className="row">
                            <div className="col-lg-4">
                                ISBN:
                            </div>
                            <div className="col-lg-4">
                                <input type="text" className="form-control"
                                    id="bookIsbn" value={this.state.book.bookIsbn} onChange={this.changeHandler} />
                            </div>
                        </div>

                    </Modal>
                </div>

            </>
        )
    }
}
export default (withRouter(Book));
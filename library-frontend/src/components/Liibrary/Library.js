import React, { Component } from 'react';
import { restService } from '../Service/RESTService'
import { withRouter, Link } from 'react-router-dom'
import Modal from '../Modal/Modal'

class Library extends Component {

    constructor(props) {
        super(props)
    }

    state = {
        librarys: [],
        library: { libraryName: "" },
        modal: {
            show: false
        }
    }

    componentDidMount() {
        this.getAllLibraries();
    }

    getAllLibraries = () => {
        restService.getAllLibraries().then((data) => {
            this.setState({
                librarys: data
            })
        })
            .catch(error => {
                console.log(error)
            });
    }

    onChangeHandler = (e) => {
        let library = Object.assign({});
        library[e.target.id] = e.target.value;
        this.setState({ library: library })
    }

    openModalHandler = () => {
        this.setState({
            library: { libraryName: "" },
            modal: {
                show: true
            }
        })
    }

    closeModalHandler = () => {
        this.setState({
            modal: {
                show: false
            },
        })
    }

    onOkButtonClickHandler = () => {
        restService.addLibrary(this.state.library)
            .then((data) => {
                if (data.status < 400) {
                    this.getAllLibraries();
                    this.setState({
                        modal: {
                            show: false
                        },
                        library: { libraryName: "" }
                    })
                }
            })
            .catch(error => {
                console.log(error)
            });
    }

    render() {
        return (
            <>
                <h3>Library Application</h3>
                <div className="panel panel-default">
                    <div className="panel-heading">Create Library</div>
                    <div className="panel-body">
                        <button type="button" className="btn btn-primary" onClick={this.openModalHandler}>New</button>
                    </div>
                </div>

                {
                    this.state.librarys.length > 0 && <div className="panel panel-default table-fixed">
                        <table className="table">
                            <thead>
                                <tr>
                                    <th className="col-width-130">S. No</th>
                                    <th>Library Name</th>
                                </tr>
                            </thead>
                            <tbody>
                                {
                                    this.state.librarys.map((library, index) => {
                                        return (
                                            <tr key={"tableRow" + index}>
                                                <td>{index + 1}</td>
                                                <td>
                                                    <Link className="inline-link" to={{
                                                        pathname: '/addBooks',
                                                        state: {
                                                            libraryId: library.libraryId
                                                        }
                                                    }}>{library.libraryName}</Link>
                                                </td>

                                            </tr>
                                        )

                                    })}
                            </tbody>
                        </table>
                    </div>
                }

                <Modal title="Add New Library"
                    show={this.state.modal.show}
                    handleClose={this.closeModalHandler}
                    handleOk={this.onOkButtonClickHandler}
                    labelOk="Add"
                    labelClose="Exit">
                    <div className="row">
                        <div className="col-lg-4">
                            Library Name:
                        </div>
                        <div className="col-lg-8">
                            <input type="text"
                                onChange={this.onChangeHandler}
                                value={this.state.library.libraryName}
                                id="libraryName" />
                        </div>
                    </div>
                </Modal>
            </>
        )
    }
}

export default (withRouter(Library));
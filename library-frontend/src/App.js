import React, { Component } from 'react';
import Layout from './components/Layout/Layout';
import { Route, Switch, withRouter } from 'react-router-dom';
import Library from './components/Liibrary/Library'
import Book from './components/Book/Book'
import Error from './components/Error/Error'


class App extends Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <Layout>
                <Switch>
                    <Route
                        path="/error"
                        render={() => (<Error />)}
                    />
                    <Route
                        path="/addBooks"
                        render={() => (<Book />)}
                    />
                    <Route
                        path="/"
                        render={() => (<Library />)}
                    />

                    <Route render={() => <h1>Not Found</h1>} />
                </Switch>
            </Layout>
        )
    }
}

export default (withRouter(App));
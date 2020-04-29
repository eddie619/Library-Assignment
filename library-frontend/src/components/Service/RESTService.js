
const FETCH_LIBRARY_API = '/library/rest/api/v1/details/getAll';
const ADD_LIBRARY_API = '/library/rest/api/v1/details/add';
const FETCH_BOOKS_API = '/library/rest/api/v1/details/book/get/';
const ADD_BOOKS_API = '/library/rest/api/v1/details/book/add';

export class RESTService {

    getAllLibraries() {
        return fetch(FETCH_LIBRARY_API, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        }).then(checkStatus).then((response) => response.json());
    }

    addLibrary(object) {
        return fetch(ADD_LIBRARY_API, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(object)
        }).then(checkStatus);
    }

    getBooksByLibrary(libraryId) {
        let URL = FETCH_BOOKS_API + libraryId;
        return fetch(URL, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        }).then(checkStatus).then((response) => response.json());
    }

    addBook(object) {
        return fetch(ADD_BOOKS_API, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(object)
        }).then(checkStatus);
    }
}


const checkStatus = response => {
    if (response.status >= 200 && response.status < 300) {
        return response;
    }
    return response.json().then(json => {
        return Promise.reject({
            body: json
        });
    });
};
const restService = new RESTService();
export { restService }
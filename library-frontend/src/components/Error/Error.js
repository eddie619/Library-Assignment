import React from 'react';
import { withRouter } from 'react-router-dom'

const Error = (props) => {

	return (
		<div className="row">
			<div className="large-body col-sm-12 pl-4 mt-4">
				<p>An error occurred while attempting to process your request.</p>
				<p>Additional information follows : </p>
				<p>An unknown system error has occurred.</p>
			</div>
		</div>
	);
};

export default (withRouter(Error));

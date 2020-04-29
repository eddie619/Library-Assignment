import React, { Fragment } from 'react';

const Layout = (props) => {
	return (
		<Fragment>
			<div className="container">
				<x-main>{props.children}</x-main>
			</div>
		</Fragment>
	);
};

export default Layout;

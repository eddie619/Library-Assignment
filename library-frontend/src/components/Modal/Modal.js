import React from 'react';

const Modal = (props) => {
	const showHideClassName = props.show ? 'display-block' : 'modal display-none';

	return (
		<div className={showHideClassName}>
			<div className="modal-backdrop" >
				<div id="myModal" role="dialog" >
					<div className="modal-dialog margin-top">
						<div className="modal-content">
							<div className="modal-header modal-header padding-bottom-8">
								<h3 className="modal-title inline-block">{props.title}</h3>
								<button type="button" className="close" data-dismiss="modal" id="close" onClick={props.handleClose}>
									X
							</button>
							</div>
							<div className="modal-body overflow-auto">{props.children}</div>
							{
								<div className="modal-footer pr-30">
									<button type="button" className="btn btn-primary" id="ok" onClick={props.handleOk} onMouseOut={e => e.target.blur()}>
										{props.labelOk ? props.labelOk : "Ok"}
									</button>
									<button type="button" className="btn btn-default" id="cancel" onClick={props.handleClose} onMouseOut={e => e.target.blur()}>
										{props.labelClose ? props.labelClose : "Cancel"}
									</button>
								</div>}

						</div>
					</div>
				</div>
			</div>
		</div>
	);
};

export default Modal;
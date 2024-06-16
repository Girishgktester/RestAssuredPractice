package com.rest.pojo.simple;

public class WorkspaceRoot {

	public WorkspaceRoot() {

	}

	private Workspace workspace;

	public WorkspaceRoot(Workspace workspace) {
		this.workspace = workspace;
	}

	public Workspace getWorkspace() {
		return workspace;
	}

	public void setWorkspace(Workspace workspace) {
		this.workspace = workspace;
	}

}

package org.opennaas.core.resources.tests.helpers.mocks.actions;

import java.util.List;

import org.opennaas.core.resources.action.ActionSet;

public class MockActionSet extends ActionSet {
	private static String	actionIdCapabilityTwo	= "actionCapabilityTwo";
	private static String	actionIdCapability		= "actionCapability";
	private static String	actionIdMock			= "actionMock";

	public MockActionSet() {
		this.putAction(actionIdMock, MockAction.class);
		this.putAction(actionIdCapability, MockCapAction.class);
		this.putAction(actionIdCapabilityTwo, MockCapActionTwo.class);
	}

	@Override
	public List<String> getRefreshActionName() {
		// TODO
		return null;
	}

}

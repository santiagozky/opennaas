/**
 * This file was auto-generated by mofcomp -j version 1.0.0 on Wed Jan 12
 * 09:21:06 CET 2011.
 */

package org.opennaas.extensions.router.model;

import java.io.Serializable;
import java.util.List;

/**
 * This Class contains accessor and mutator methods for all properties defined in the CIM class LogicalPort as well as methods comparable to the
 * invokeMethods defined for this class. This Class implements the LogicalPortBean Interface. The CIM class LogicalPort is described as follows:
 * 
 * The abstraction of a port or connection point of a Device. This object should be instantiated when the Port has independent management
 * characteristics from the Device that includes it. Examples are a Fibre Channel Port and a USB Port.
 */
public class LogicalPort extends LogicalDevice implements Serializable {

	/**
	 * Add a new PortImplementsEndpoint association between protocolEndpoint and this element
	 * 
	 * @param protocolEndpoint
	 * @return
	 */
	public boolean addProtocolEndpoint(ProtocolEndpoint protocolEndpoint) {
		if (protocolEndpoint == null)
			return false;
		return (PortImplementsEndpoint.link(this, protocolEndpoint) != null);
	}

	/**
	 * Remove the PortImplementsEndpoint association (will be deleted) between the protocolEndpoint and this element
	 * 
	 * @param protocolEndpoint
	 * @return
	 */
	public boolean removeProtocolEndpoint(ProtocolEndpoint protocolEndpoint) {

		if (protocolEndpoint == null)
			return false;
		Association a = this.getToAssociationByElement(protocolEndpoint);
		if (a == null)
			return false;
		else {
			a.unlink();
			return true;
		}
	}

	public boolean cleanProtocolEndpoint() {

		for (ProtocolEndpoint protocolEndpoint : getProtocolEndpoint()) {
			boolean result = this.removeProtocolEndpoint(protocolEndpoint);
			if (result == false)
				return false;
		}
		return true;

	}

	public void setProtocolEndpoints(List<ProtocolEndpoint> protocolEndpoints) {

		this.cleanProtocolEndpoint();
		for (ProtocolEndpoint protocolEndpoint : protocolEndpoints) {
			this.addProtocolEndpoint(protocolEndpoint);
		}

	}

	/**
	 * This method returns the list of ProtocolEndpoint from the toAssociation vector that match with the type PortImplementsEndpoint the association
	 * wouldn't be deleted
	 * 
	 * @return List<ProtocolEndpoint>
	 */
	@SuppressWarnings("unchecked")
	public List<ProtocolEndpoint> getProtocolEndpoint() {
		return (List<ProtocolEndpoint>) this.getToAssociatedElementsByType(PortImplementsEndpoint.class);
	}

	/**
	 * MANUALLY ADDED TO CIM (14/04/2011) <br>
	 * Adds given device to the association PortsOnDevice
	 * 
	 * @param logicalDevice
	 * @return
	 */
	public boolean addDevice(LogicalDevice logicalDevice) {

		// TODO check if it is allready added

		if (logicalDevice == null)
			return false;
		return (PortOnDevice.link(logicalDevice, this) != null);
	}

	/**
	 * MANUALLY ADDED TO CIM (14/04/2011)<br>
	 * Removes given device from the association PortsOnDevice
	 * 
	 * @return
	 */
	public boolean removeDevice(LogicalDevice logicalDevice) {
		if (logicalDevice == null)
			return false;
		Association a = this.getFromAssociationByElement(logicalDevice);
		if (a == null)
			return false;
		else {
			a.unlink();
			return true;
		}
	}

	/**
	 * MANUALLY ADDED TO CIM (14/04/2011)<br>
	 * Get Devices associated with this trhough PortsOnDevice.
	 * 
	 * @return list of devices having this port
	 */
	public List<LogicalDevice> getDevices() {
		return (List<LogicalDevice>) this.getFromAssociatedElementsByType(PortOnDevice.class);
	}

	/**
	 * This constructor creates a LogicalPortBeanImpl Class which implements the LogicalPortBean Interface, and encapsulates the CIM class LogicalPort
	 * in a Java Bean. The CIM class LogicalPort is described as follows:
	 * 
	 * The abstraction of a port or connection point of a Device. This object should be instantiated when the Port has independent management
	 * characteristics from the Device that includes it. Examples are a Fibre Channel Port and a USB Port.
	 */
	public LogicalPort() {
	};

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property speed.
	 */
	private long	speed;

	/**
	 * This method returns the LogicalPort.speed property value. This property is described as follows:
	 * 
	 * The bandwidth of the Port in Bits per Second.
	 * 
	 * @return long current speed property value
	 * @exception Exception
	 */
	public long getSpeed() {

		return this.speed;
	} // getSpeed

	/**
	 * This method sets the LogicalPort.speed property value. This property is described as follows:
	 * 
	 * The bandwidth of the Port in Bits per Second.
	 * 
	 * @param long new speed property value
	 * @exception Exception
	 */
	public void setSpeed(long speed) {

		this.speed = speed;
	} // setSpeed

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property maxSpeed.
	 */
	private long	maxSpeed;

	/**
	 * This method returns the LogicalPort.maxSpeed property value. This property is described as follows:
	 * 
	 * The maximum bandwidth of the Port in Bits per Second.
	 * 
	 * @return long current maxSpeed property value
	 * @exception Exception
	 */
	public long getMaxSpeed() {

		return this.maxSpeed;
	} // getMaxSpeed

	/**
	 * This method sets the LogicalPort.maxSpeed property value. This property is described as follows:
	 * 
	 * The maximum bandwidth of the Port in Bits per Second.
	 * 
	 * @param long new maxSpeed property value
	 * @exception Exception
	 */
	public void setMaxSpeed(long maxSpeed) {

		this.maxSpeed = maxSpeed;
	} // setMaxSpeed

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property requestedSpeed.
	 */
	private long	requestedSpeed;

	/**
	 * This method returns the LogicalPort.requestedSpeed property value. This property is described as follows:
	 * 
	 * The requested bandwidth of the Port in Bits per Second. The actual bandwidth is reported in LogicalPort.Speed.
	 * 
	 * @return long current requestedSpeed property value
	 * @exception Exception
	 */
	public long getRequestedSpeed() {

		return this.requestedSpeed;
	} // getRequestedSpeed

	/**
	 * This method sets the LogicalPort.requestedSpeed property value. This property is described as follows:
	 * 
	 * The requested bandwidth of the Port in Bits per Second. The actual bandwidth is reported in LogicalPort.Speed.
	 * 
	 * @param long new requestedSpeed property value
	 * @exception Exception
	 */
	public void setRequestedSpeed(long requestedSpeed) {

		this.requestedSpeed = requestedSpeed;
	} // setRequestedSpeed

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property UsageRestriction.
	 */

	public enum UsageRestriction {
		UNKNOWN,
		FRONT_END_ONLY,
		BACK_END_ONLY,
		NOT_RESTRICTED
	}

	private UsageRestriction	usageRestriction;

	/**
	 * This method returns the LogicalPort.usageRestriction property value. This property is described as follows:
	 * 
	 * In some circumstances, a LogicalPort might be identifiable as a front end or back end port. An example of this situation would be a storage
	 * array that might have back end ports to communicate with disk drives and front end ports to communicate with hosts. If there is no restriction
	 * on the use of the port, then the value should be set to 'not restricted'.
	 * 
	 * @return int current usageRestriction property value
	 * @exception Exception
	 */
	public UsageRestriction getUsageRestriction() {

		return this.usageRestriction;
	} // getUsageRestriction

	/**
	 * This method sets the LogicalPort.usageRestriction property value. This property is described as follows:
	 * 
	 * In some circumstances, a LogicalPort might be identifiable as a front end or back end port. An example of this situation would be a storage
	 * array that might have back end ports to communicate with disk drives and front end ports to communicate with hosts. If there is no restriction
	 * on the use of the port, then the value should be set to 'not restricted'.
	 * 
	 * @param int new usageRestriction property value
	 * @exception Exception
	 */
	public void setUsageRestriction(UsageRestriction usageRestriction) {

		this.usageRestriction = usageRestriction;
	} // setUsageRestriction

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property PortType.
	 */

	public enum PortType {
		UNKNOWN,
		OTHER,
		NOT_APPLICABLE,
		DMTF_RESERVED,
		VENDOR_RESERVED
	}

	private PortType	portType;

	/**
	 * This method returns the LogicalPort.portType property value. This property is described as follows:
	 * 
	 * PortType is defined to force consistent naming of the 'type' property in subclasses and to guarantee unique enum values for all instances of
	 * NetworkPort. When set to 1 ("Other"), related property OtherPortType contains a string description of the type of port. A range of values,
	 * DMTF_Reserved, has been defined that allows subclasses to override and define their specific types of ports.
	 * 
	 * @return int current portType property value
	 * @exception Exception
	 */
	public PortType getPortType() {

		return this.portType;
	} // getPortType

	/**
	 * This method sets the LogicalPort.portType property value. This property is described as follows:
	 * 
	 * PortType is defined to force consistent naming of the 'type' property in subclasses and to guarantee unique enum values for all instances of
	 * NetworkPort. When set to 1 ("Other"), related property OtherPortType contains a string description of the type of port. A range of values,
	 * DMTF_Reserved, has been defined that allows subclasses to override and define their specific types of ports.
	 * 
	 * @param int new portType property value
	 * @exception Exception
	 */
	public void setPortType(PortType portType) {

		this.portType = portType;
	} // setPortType

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property otherPortType.
	 */
	private String	otherPortType;

	/**
	 * This method returns the LogicalPort.otherPortType property value. This property is described as follows:
	 * 
	 * Describes the type of module, when PortType is set to 1 ("Other").
	 * 
	 * @return String current otherPortType property value
	 * @exception Exception
	 */
	public String getOtherPortType() {

		return this.otherPortType;
	} // getOtherPortType

	/**
	 * This method sets the LogicalPort.otherPortType property value. This property is described as follows:
	 * 
	 * Describes the type of module, when PortType is set to 1 ("Other").
	 * 
	 * @param String
	 *            new otherPortType property value
	 * @exception Exception
	 */
	public void setOtherPortType(String otherPortType) {

		this.otherPortType = otherPortType;
	} // setOtherPortType

} // Class LogicalPort
/**
 * This file was auto-generated by mofcomp -j version 1.0.0 on Wed Jan 12
 * 09:21:06 CET 2011.
 */

package net.i2cat.mantychore.model;

import java.io.*;
import java.lang.Exception;

/**
 * This Class contains accessor and mutator methods for all properties defined in the CIM class MeterService as well as methods comparable to the
 * invokeMethods defined for this class. This Class implements the MeterServiceBean Interface. The CIM class MeterService is described as follows:
 * 
 * This class represents the metering of network traffic. Metering is the function of monitoring the arrival times of packets of a traffic stream and
 * determining the level of conformance of each packet with respect to a pre- established traffic profile. A meter has the ability to invoke different
 * ConditioningServices for conforming and non-conforming traffic. Traffic leaving a meter may be further conditioned (e.g., dropped or queued) by
 * routing the packet to another conditioning element. This class is modeled as a ConditioningService so that it can be aggregated into a QoSService
 * (using the QoSConditioningSubService association), to indicate that its functionality underlies that QoS service. MeterService also participates in
 * a subclass of the NextService association, to identify the subsequent ConditioningServices for conforming and non-conforming traffic.
 */
public class MeterService extends ConditioningService implements Serializable
{

	/**
	 * This constructor creates a MeterServiceBeanImpl Class which implements the MeterServiceBean Interface, and encapsulates the CIM class
	 * MeterService in a Java Bean. The CIM class MeterService is described as follows:
	 * 
	 * This class represents the metering of network traffic. Metering is the function of monitoring the arrival times of packets of a traffic stream
	 * and determining the level of conformance of each packet with respect to a pre- established traffic profile. A meter has the ability to invoke
	 * different ConditioningServices for conforming and non-conforming traffic. Traffic leaving a meter may be further conditioned (e.g., dropped or
	 * queued) by routing the packet to another conditioning element. This class is modeled as a ConditioningService so that it can be aggregated into
	 * a QoSService (using the QoSConditioningSubService association), to indicate that its functionality underlies that QoS service. MeterService
	 * also participates in a subclass of the NextService association, to identify the subsequent ConditioningServices for conforming and
	 * non-conforming traffic.
	 */
	public MeterService() {
	};

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property MeterType.
	 */

	public enum MeterType {
		OTHER,
		AVERAGE_RATE_METER,
		EXPONENTIALLY_WEIGHTED_MOVING_AVERAGE_METER,
		TOKEN_BUCKET_METER
	}

	private MeterType	meterType;

	/**
	 * This method returns the MeterService.meterType property value. This property is described as follows:
	 * 
	 * This property is an enumerated 16-bit unsigned integer that is used to specify the particular type of meter. Defined values of the enumeration
	 * are: 1: Other 2: Average Rate Meter 3: Exponentially Weighted Moving Average Meter 4: Token Bucket Meter Note: The MeterType property and the
	 * MeterService subclasses provide similar information. This property is defined for query purposes and for future expansion. It is assumed that
	 * not all MeterServices will require a subclass to define them. Therefore, MeterService will be instantiated directly and the Type property is
	 * needed.
	 * 
	 * @return int current meterType property value
	 * @exception Exception
	 */
	public MeterType getMeterType() {

		return this.meterType;
	} // getMeterType

	/**
	 * This method sets the MeterService.meterType property value. This property is described as follows:
	 * 
	 * This property is an enumerated 16-bit unsigned integer that is used to specify the particular type of meter. Defined values of the enumeration
	 * are: 1: Other 2: Average Rate Meter 3: Exponentially Weighted Moving Average Meter 4: Token Bucket Meter Note: The MeterType property and the
	 * MeterService subclasses provide similar information. This property is defined for query purposes and for future expansion. It is assumed that
	 * not all MeterServices will require a subclass to define them. Therefore, MeterService will be instantiated directly and the Type property is
	 * needed.
	 * 
	 * @param int new meterType property value
	 * @exception Exception
	 */
	public void setMeterType(MeterType meterType) {

		this.meterType = meterType;
	} // setMeterType

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property otherMeterType.
	 */
	private String	otherMeterType;

	/**
	 * This method returns the MeterService.otherMeterType property value. This property is described as follows:
	 * 
	 * This property is a string used in conjunction with the MeterType property. When the value of MeterType is 1 (i.e., "Other"), then the name of
	 * the conformance level for this meter is defined in this property.
	 * 
	 * @return String current otherMeterType property value
	 * @exception Exception
	 */
	public String getOtherMeterType() {

		return this.otherMeterType;
	} // getOtherMeterType

	/**
	 * This method sets the MeterService.otherMeterType property value. This property is described as follows:
	 * 
	 * This property is a string used in conjunction with the MeterType property. When the value of MeterType is 1 (i.e., "Other"), then the name of
	 * the conformance level for this meter is defined in this property.
	 * 
	 * @param String
	 *            new otherMeterType property value
	 * @exception Exception
	 */
	public void setOtherMeterType(String otherMeterType) {

		this.otherMeterType = otherMeterType;
	} // setOtherMeterType

	/**
	 * The following constants are defined for use with the ValueMap/Values qualified property conformanceLevels.
	 */
	private int	conformanceLevels;

	/**
	 * This method returns the MeterService.conformanceLevels property value. This property is described as follows:
	 * 
	 * An unsigned integer indicating the number of conformance levels supported by the Meter. For example, when only 'in-profile' or 'out of profile'
	 * metering is supported. ConformanceLevels is set to 2.
	 * 
	 * @return int current conformanceLevels property value
	 * @exception Exception
	 */
	public int getConformanceLevels() {

		return this.conformanceLevels;
	} // getConformanceLevels

	/**
	 * This method sets the MeterService.conformanceLevels property value. This property is described as follows:
	 * 
	 * An unsigned integer indicating the number of conformance levels supported by the Meter. For example, when only 'in-profile' or 'out of profile'
	 * metering is supported. ConformanceLevels is set to 2.
	 * 
	 * @param int new conformanceLevels property value
	 * @exception Exception
	 */
	public void setConformanceLevels(int conformanceLevels) {

		this.conformanceLevels = conformanceLevels;
	} // setConformanceLevels

} // Class MeterService
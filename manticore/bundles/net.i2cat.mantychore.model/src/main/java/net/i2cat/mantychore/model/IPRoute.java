/**
 * This file was auto-generated by mofcomp -j version 1.0.0 on Wed Jan 12 
 * 09:21:06 CET 2011. 
 */

package net.i2cat.mantychore.model;

import java.io.*;
import java.lang.Exception;

/**
 * This Class contains accessor and mutator methods for all properties defined 
 * in the CIM class IPRoute as well as methods comparable to the 
 * invokeMethods defined for this class. This Class implements the 
 * IPRouteBean Interface. The CIM class IPRoute is described as follows: 
 * 
 * An IPRoute relates a destination address to the address or interface 
 * through which the remote address may be reached. The destination address 
 * may be a specific IP endpoint or a subnet, dependent on the mask. An 
 * instance of this class represents either static or dynamic routing. Static 
 * routes are distinguished by setting the IsStatic boolean property to TRUE. 
 * Since many routes between endpoints can be defined (using different route 
 * calculation algorithms), the CIM_IPRoute class is defined as Abstract. 
 * This forces subclassing (for example, see CIM_BGPIPRoute) and allows the 
 * instances of its subclasses to be distinguished based on their 
 * CreationClassName key property. IPRoute is deprecated in lieu of the more 
 * general, concrete NextHopIPRoute class. NextHopIPRoute allows the 
 * definition of BOTH a next hop address and an interface for transmission of 
 * the traffic. Also, it does not mandate the instantiation of a 
 * ForwardingService class. 
 */
    @Deprecated
public class IPRoute extends NextHopRouting implements Serializable {

    /**
     * This constructor creates a IPRouteBeanImpl Class which implements the 
     * IPRouteBean Interface, and encapsulates the CIM class IPRoute in a 
     * Java Bean. The CIM class IPRoute is described as follows: 
     * 
     * An IPRoute relates a destination address to the address or interface 
     * through which the remote address may be reached. The destination 
     * address may be a specific IP endpoint or a subnet, dependent on the 
     * mask. An instance of this class represents either static or dynamic 
     * routing. Static routes are distinguished by setting the IsStatic 
     * boolean property to TRUE. Since many routes between endpoints can be 
     * defined (using different route calculation algorithms), the 
     * CIM_IPRoute class is defined as Abstract. This forces subclassing (for 
     * example, see CIM_BGPIPRoute) and allows the instances of its 
     * subclasses to be distinguished based on their CreationClassName key 
     * property. IPRoute is deprecated in lieu of the more general, concrete 
     * NextHopIPRoute class. NextHopIPRoute allows the definition of BOTH a 
     * next hop address and an interface for transmission of the traffic. 
     * Also, it does not mandate the instantiation of a ForwardingService 
     * class. 
     */
    protected IPRoute(){};
    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property systemCreationClassName. 
     */
    @Deprecated    private String systemCreationClassName;
    /**
     * This method returns the IPRoute.systemCreationClassName property value. 
     * This property is described as follows: 
     * 
     * The scoping ForwardingService's SystemCreationClassName.
     * 
     * @return	String	current systemCreationClassName property 
     * value 
     * @exception	Exception	
     */
    @Deprecated
    public String getSystemCreationClassName(){

    return this.systemCreationClassName;
    } // getSystemCreationClassName

    /**
     * This method sets the IPRoute.systemCreationClassName property value. 
     * This property is described as follows: 
     * 
     * The scoping ForwardingService's SystemCreationClassName.
     * 
     * @param	String	new systemCreationClassName property value
     * @exception	Exception	
     */
    @Deprecated
    public void setSystemCreationClassName(String systemCreationClassName) {

    this.systemCreationClassName = systemCreationClassName;
    } // setSystemCreationClassName


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property systemName. 
     */
    @Deprecated    private String systemName;
    /**
     * This method returns the IPRoute.systemName property value. This 
     * property is described as follows: 
     * 
     * The scoping ForwardingService's SystemName.
     * 
     * @return	String	current systemName property value
     * @exception	Exception	
     */
    @Deprecated
    public String getSystemName(){

    return this.systemName;
    } // getSystemName

    /**
     * This method sets the IPRoute.systemName property value. This property 
     * is described as follows: 
     * 
     * The scoping ForwardingService's SystemName.
     * 
     * @param	String	new systemName property value
     * @exception	Exception	
     */
    @Deprecated
    public void setSystemName(String systemName) {

    this.systemName = systemName;
    } // setSystemName


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property serviceCreationClassName. 
     */
    @Deprecated    private String serviceCreationClassName;
    /**
     * This method returns the IPRoute.serviceCreationClassName property 
     * value. This property is described as follows: 
     * 
     * The scoping ForwardingService's CreationClassName.
     * 
     * @return	String	current serviceCreationClassName property 
     * value 
     * @exception	Exception	
     */
    @Deprecated
    public String getServiceCreationClassName(){

    return this.serviceCreationClassName;
    } // getServiceCreationClassName

    /**
     * This method sets the IPRoute.serviceCreationClassName property value. 
     * This property is described as follows: 
     * 
     * The scoping ForwardingService's CreationClassName.
     * 
     * @param	String	new serviceCreationClassName property value
     * @exception	Exception	
     */
    @Deprecated
    public void setServiceCreationClassName(String serviceCreationClassName) 
	{

    this.serviceCreationClassName = serviceCreationClassName;
    } // setServiceCreationClassName


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property serviceName. 
     */
    @Deprecated    private String serviceName;
    /**
     * This method returns the IPRoute.serviceName property value. This 
     * property is described as follows: 
     * 
     * The scoping ForwardingService's Name.
     * 
     * @return	String	current serviceName property value
     * @exception	Exception	
     */
    @Deprecated
    public String getServiceName(){

    return this.serviceName;
    } // getServiceName

    /**
     * This method sets the IPRoute.serviceName property value. This property 
     * is described as follows: 
     * 
     * The scoping ForwardingService's Name.
     * 
     * @param	String	new serviceName property value
     * @exception	Exception	
     */
    @Deprecated
    public void setServiceName(String serviceName) {

    this.serviceName = serviceName;
    } // setServiceName


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property creationClassName. 
     */
    @Deprecated    private String creationClassName;
    /**
     * This method returns the IPRoute.creationClassName property value. This 
     * property is described as follows: 
     * 
     * CreationClassName indicates the name of the class or the subclass used 
     * in the creation of an instance. When used with the other key 
     * properties of this class, this property allows all instances of this 
     * class and its subclasses to be uniquely identified. 
     * 
     * @return	String	current creationClassName property value
     * @exception	Exception	
     */
    @Deprecated
    public String getCreationClassName(){

    return this.creationClassName;
    } // getCreationClassName

    /**
     * This method sets the IPRoute.creationClassName property value. This 
     * property is described as follows: 
     * 
     * CreationClassName indicates the name of the class or the subclass used 
     * in the creation of an instance. When used with the other key 
     * properties of this class, this property allows all instances of this 
     * class and its subclasses to be uniquely identified. 
     * 
     * @param	String	new creationClassName property value
     * @exception	Exception	
     */
    @Deprecated
    public void setCreationClassName(String creationClassName) {

    this.creationClassName = creationClassName;
    } // setCreationClassName


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property iPDestinationAddress. 
     */
    @Deprecated    private String iPDestinationAddress;
    /**
     * This method returns the IPRoute.iPDestinationAddress property value. 
     * This property is described as follows: 
     * 
     * The IP address which serves as the destination of the traffic, 
     * formatted according to the appropriate convention as defined in the 
     * AddressType property of this class. This property has the same 
     * semantics as DestinationAddress inherited from the NextHopRouting 
     * superclass, but a different property name. This is because this 
     * property and class were defined before NextHopRouting and are Key 
     * properties. They cannot be removed. ModelCorrespondence indicates that 
     * they should be set to equivalent values for consistency and ease of 
     * query. 
     * 
     * @return	String	current iPDestinationAddress property 
     * value 
     * @exception	Exception	
     */
    @Deprecated
    public String getIPDestinationAddress(){

    return this.iPDestinationAddress;
    } // getIPDestinationAddress

    /**
     * This method sets the IPRoute.iPDestinationAddress property value. This 
     * property is described as follows: 
     * 
     * The IP address which serves as the destination of the traffic, 
     * formatted according to the appropriate convention as defined in the 
     * AddressType property of this class. This property has the same 
     * semantics as DestinationAddress inherited from the NextHopRouting 
     * superclass, but a different property name. This is because this 
     * property and class were defined before NextHopRouting and are Key 
     * properties. They cannot be removed. ModelCorrespondence indicates that 
     * they should be set to equivalent values for consistency and ease of 
     * query. 
     * 
     * @param	String	new iPDestinationAddress property value
     * @exception	Exception	
     */
    @Deprecated
    public void setIPDestinationAddress(String iPDestinationAddress) {

    this.iPDestinationAddress = iPDestinationAddress;
    } // setIPDestinationAddress


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property iPDestinationMask. 
     */
    @Deprecated    private String iPDestinationMask;
    /**
     * This method returns the IPRoute.iPDestinationMask property value. This 
     * property is described as follows: 
     * 
     * The mask for the destination IP address, formatted according to the 
     * appropriate convention as defined in the AddressType property of this 
     * class. This property has the same semantics as DestinationMask 
     * inherited from the NextHopRouting superclass, but a different property 
     * name. This is because this property and class were defined before 
     * NextHopRouting and are Key properties. They cannot be removed. 
     * ModelCorrespondence indicates that they should be set to equivalent 
     * values for consistency and ease of query. 
     * 
     * @return	String	current iPDestinationMask property value
     * @exception	Exception	
     */
    @Deprecated
    public String getIPDestinationMask(){

    return this.iPDestinationMask;
    } // getIPDestinationMask

    /**
     * This method sets the IPRoute.iPDestinationMask property value. This 
     * property is described as follows: 
     * 
     * The mask for the destination IP address, formatted according to the 
     * appropriate convention as defined in the AddressType property of this 
     * class. This property has the same semantics as DestinationMask 
     * inherited from the NextHopRouting superclass, but a different property 
     * name. This is because this property and class were defined before 
     * NextHopRouting and are Key properties. They cannot be removed. 
     * ModelCorrespondence indicates that they should be set to equivalent 
     * values for consistency and ease of query. 
     * 
     * @param	String	new iPDestinationMask property value
     * @exception	Exception	
     */
    @Deprecated
    public void setIPDestinationMask(String iPDestinationMask) {

    this.iPDestinationMask = iPDestinationMask;
    } // setIPDestinationMask


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property AddressType. 
     */
    @Deprecated
    public enum AddressType{
    UNKNOWN,
    IPV4,
    IPV6
    }
    @Deprecated
    private AddressType addressType;
    /**
     * This method returns the IPRoute.addressType property value. This 
     * property is described as follows: 
     * 
     * An enumeration that describes the format of the address property. 
     * Addresses that can be formatted in IPv4 format, must be formatted that 
     * way to ensure mixed IPv4/IPv6 support. AddressType is part of the key 
     * so that an IPv4 and an IPv6 route to IP subnets with the same network 
     * number but different IP versions (v4/v6) can coexist. 
     * 
     * @return	int	current addressType property value
     * @exception	Exception	
     */
    @Deprecated
    public AddressType getAddressType(){

    return this.addressType;
    } // getAddressType

    /**
     * This method sets the IPRoute.addressType property value. This property 
     * is described as follows: 
     * 
     * An enumeration that describes the format of the address property. 
     * Addresses that can be formatted in IPv4 format, must be formatted that 
     * way to ensure mixed IPv4/IPv6 support. AddressType is part of the key 
     * so that an IPv4 and an IPv6 route to IP subnets with the same network 
     * number but different IP versions (v4/v6) can coexist. 
     * 
     * @param	int	new addressType property value
     * @exception	Exception	
     */
    @Deprecated
    public void setAddressType(AddressType addressType){

    this.addressType = addressType;
    } // setAddressType


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property destinationAddress. 
     */
    @Deprecated    private String destinationAddress;
    /**
     * This method returns the IPRoute.destinationAddress property value. This 
     * property is described as follows: 
     * 
     * The address which serves as the destination to be reached.
     * 
     * @return	String	current destinationAddress property value
     * @exception	Exception	
     */
    @Override
	@Deprecated
    public String getDestinationAddress(){

    return this.destinationAddress;
    } // getDestinationAddress

    /**
     * This method sets the IPRoute.destinationAddress property value. This 
     * property is described as follows: 
     * 
     * The address which serves as the destination to be reached.
     * 
     * @param	String	new destinationAddress property value
     * @exception	Exception	
     */
    @Override
	@Deprecated
    public void setDestinationAddress(String destinationAddress) {

    this.destinationAddress = destinationAddress;
    } // setDestinationAddress


    /**
     * The following constants are defined for use with the ValueMap/Values 
     * qualified property destinationMask. 
     */
    @Deprecated    private String destinationMask;
    /**
     * This method returns the IPRoute.destinationMask property value. This 
     * property is described as follows: 
     * 
     * The mask for the DestinationAddress.
     * 
     * @return	String	current destinationMask property value
     * @exception	Exception	
     */
    @Override
	@Deprecated
    public String getDestinationMask(){

    return this.destinationMask;
    } // getDestinationMask

    /**
     * This method sets the IPRoute.destinationMask property value. This 
     * property is described as follows: 
     * 
     * The mask for the DestinationAddress.
     * 
     * @param	String	new destinationMask property value
     * @exception	Exception	
     */
    @Override
	@Deprecated
    public void setDestinationMask(String destinationMask) {

    this.destinationMask = destinationMask;
    } // setDestinationMask



} // Class IPRoute
package org.opennaas.extensions.idb.notification;

import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.AddTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetTopicsResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.GetTopicsType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.PublishResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.PublishType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.RemoveTopicType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.SubscribeType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeResponseType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.UnsubscribeType;
import org.opennaas.extensions.idb.serviceinterface.databinding.jaxb.exceptions.TopicNotFoundFaultException;

public interface INotificationCapabilityService {

	/**
	 * addTopic Handler.
	 * 
	 * @param element
	 *            addTopic Request
	 * @return addTopic Response
	 */
	public abstract AddTopicResponseType addTopic(final AddTopicType element);

	/**
	 * getTopics Handler.
	 * 
	 * @param element
	 *            getTopics Request
	 * @return getTopics Response
	 */
	public abstract GetTopicsResponseType getTopics(final GetTopicsType element);

	/**
	 * publish Handler.
	 * 
	 * @param element
	 *            publish Request
	 * @return publish Response
	 * @throws TopicNotFoundFaultException
	 */
	public abstract PublishResponseType publish(final PublishType element)
			throws TopicNotFoundFaultException;

	/**
	 * removeTopic Handler.
	 * 
	 * @param element
	 *            removeTopic Request
	 * @return removeTopic Response
	 */
	public abstract RemoveTopicResponseType removeTopic(
			final RemoveTopicType element);

	/**
	 * subscribe Handler.
	 * 
	 * @param element
	 *            subscribe Request
	 * @return subscribe Response
	 * @throws TopicNotFoundFaultException
	 */
	public abstract SubscribeResponseType subscribe(final SubscribeType element)
			throws TopicNotFoundFaultException;

	/**
	 * unsubscribe Handler.
	 * 
	 * @param element
	 *            unsubscribe Request
	 * @return unsubscribe Response
	 * @throws TopicNotFoundFaultException
	 */
	public abstract UnsubscribeResponseType unsubscribe(
			final UnsubscribeType element) throws TopicNotFoundFaultException;

}
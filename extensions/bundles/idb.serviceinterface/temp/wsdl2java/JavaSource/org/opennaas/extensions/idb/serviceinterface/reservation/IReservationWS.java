package org.opennaas.extensions.idb.serviceinterface.reservation;

import org.w3c.dom.Element;

/**
 * reservation server.
 *
 * IReservationWS.java
 * Sat May 12 13:40:43 CEST 2012
 * Generated by the Apache Muse Code Generation Tool
 */
 public interface IReservationWS {
    String PREFIX = "tns";

    String NAMESPACE_URI = "http://opennaas.org/nsp/webservice/reservation";

    /**
     * isAvailable Handler.
     *
     * @param isAvailable Request
     * @return isAvailable Response
     * @throws Exception In case of errors
     */
    public Element isAvailable (Element isAvailable)
            throws Exception;

    /**
     * activate Handler.
     *
     * @param activate Request
     * @return activate Response
     * @throws Exception In case of errors
     */
    public Element activate (Element activate)
            throws Exception;

    /**
     * cancelJob Handler.
     *
     * @param cancelJob Request
     * @return cancelJob Response
     * @throws Exception In case of errors
     */
    public Element cancelJob (Element cancelJob)
            throws Exception;

    /**
     * createReservation Handler.
     *
     * @param createReservation Request
     * @return createReservation Response
     * @throws Exception In case of errors
     */
    public Element createReservation (Element createReservation)
            throws Exception;

    /**
     * notification Handler.
     *
     * @param notification Request
     * @return notification Response
     * @throws Exception In case of errors
     */
    public Element notification (Element notification)
            throws Exception;

    /**
     * bind Handler.
     *
     * @param bind Request
     * @return bind Response
     * @throws Exception In case of errors
     */
    public Element bind (Element bind)
            throws Exception;

    /**
     * getStatus Handler.
     *
     * @param getStatus Request
     * @return getStatus Response
     * @throws Exception In case of errors
     */
    public Element getStatus (Element getStatus)
            throws Exception;

    /**
     * completeJob Handler.
     *
     * @param completeJob Request
     * @return completeJob Response
     * @throws Exception In case of errors
     */
    public Element completeJob (Element completeJob)
            throws Exception;

    /**
     * getReservations Handler.
     *
     * @param getReservations Request
     * @return getReservations Response
     * @throws Exception In case of errors
     */
    public Element getReservations (Element getReservations)
            throws Exception;

    /**
     * cancelReservation Handler.
     *
     * @param cancelReservation Request
     * @return cancelReservation Response
     * @throws Exception In case of errors
     */
    public Element cancelReservation (Element cancelReservation)
            throws Exception;

    /**
     * getReservation Handler.
     *
     * @param getReservation Request
     * @return getReservation Response
     * @throws Exception In case of errors
     */
    public Element getReservation (Element getReservation)
            throws Exception;

}
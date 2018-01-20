/*******************************************************************************
 * Copyright (c) 2012 IBM Corporation and others.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * and Eclipse Distribution License v. 1.0 which accompanies this distribution.
 *  
 * The Eclipse Public License is available at http://www.eclipse.org/legal/epl-v10.html
 * and the Eclipse Distribution License is available at
 * http://www.eclipse.org/org/documents/edl-v10.php.
 *
 * Contributors:
 *
 *     Russell Boykin       - initial API and implementation
 *     Alberto Giammaria    - initial API and implementation
 *     Chris Peters         - initial API and implementation
 *     Gianluca Bernardini  - initial API and implementation
 *     Michael Fiedler      - Bugzilla adpater implementations
 *     Jad El-khoury        - initial implementation of code generator (https://bugs.eclipse.org/bugs/show_bug.cgi?id=422448)
 * 
 * This file is generated by org.eclipse.lyo.oslc4j.codegenerator
 *******************************************************************************/

package jira.rdf.example.com;

import org.eclipse.lyo.oslc4j.core.model.OslcConstants;

// Start of user code imports
// End of user code

public interface JiraAdaptorConstants
{
    // Start of user code user constants
    // End of user code

    public static String DUBLIN_CORE_DOMAIN = "http://purl.org/dc/terms#";
    public static String DUBLIN_CORE_NAMSPACE = "http://purl.org/dc/terms#";
    public static String DUBLIN_CORE_NAMSPACE_PREFIX = "dcterms";
    public static String JIRA_DOMAIN = "http://com.example.rdf/jira#";
    public static String JIRA_NAMSPACE = "http://com.example.rdf/jira#";
    public static String JIRA_NAMSPACE_PREFIX = "jira";
    public static String OSLC_CM_DOMAIN = "http://open-services.net/ns/cm#";
    public static String OSLC_CM_NAMSPACE = "http://open-services.net/ns/cm#";
    public static String OSLC_CM_NAMSPACE_PREFIX = "oslc_cm";

    public static String CHANGEREQUEST = "ChangeRequest";
    public static String PATH_CHANGEREQUEST = "changeRequest";
    public static String TYPE_CHANGEREQUEST = OSLC_CM_NAMSPACE + "ChangeRequest";
    public static String PROJECT = "Project";
    public static String PATH_PROJECT = "project";
    public static String TYPE_PROJECT = JIRA_NAMSPACE + "Project";

    public static final String HDR_OSLC_VERSION = "OSLC-Core-Version";

}


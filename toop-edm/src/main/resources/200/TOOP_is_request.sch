<?xml version="1.0" encoding="UTF-8"?>
<!--

    Copyright (C) 2018-2020 toop.eu

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

            http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

-->

<schema xmlns="http://purl.oclc.org/dsdl/schematron" 
    xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
    queryBinding="xslt2" 
    >
    <ns prefix="query"  uri="urn:oasis:names:tc:ebxml-regrep:xsd:query:4.0"/>
 
    
    <title>TOOP EDM - isRequest Checker</title>
    
    
    <pattern>
        <rule context="/">
            <assert test="( (exists(query:QueryRequest)) )"  flag='ERROR' id="NOT_A_REQUEST">
                The message does not look like a QueryRequest. 
            </assert>
        </rule>
    </pattern>
    
   
</schema>
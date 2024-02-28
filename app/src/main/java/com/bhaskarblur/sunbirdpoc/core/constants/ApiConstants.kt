package com.bhaskarblur.sunbirdpoc.core.constants

object ApiConstants {

    const val Identity_Base_Url = "https://4355-115-240-127-98.ngrok-free.app/"
    const val Credential_Base_Url = " https://5cae-115-240-127-98.ngrok-free.app"

    const val generateDidBody = """{
    "content": 
        [
            {
                "alsoKnownAs": ["testName", "testEmail"],
                "services": [],
                "method": "witsInnovationLab"
            }
        ]
    
}}"""

    const val issueCredBody = """{
    "credential": {
        "@context": [
            "https://www.w3.org/2018/credentials/v1",
            {
              "@context": {
                "@version": 1.1,
                "@protected": true,
                "id": "@id",
                "type": "@type",
                "schema": "https://schema.org/",
                "skillCredential": {
                  "@id": "did:skillCredential",
                  "@context": {
                    "@version": 1.1,
                    "@protected": true,
                    "id": "@id",
                    "type": "@type",
                    "instituteName":"schema:Text",
                    "skill":"schema:Text",
                    "issuedOn":"schema:Text",
                    "studentName": "schema:Text",
                    "courseName": "schema:Text"
                  }
                }
              }
            }
          ],
        "type": [
            "VerifiableCredential",
            "skillCredential"
        ],
        "issuer": "testIssuerId",
        "expirationDate": "2024-12-31T11:56:27.259Z",
        "credentialSubject": {
            "id": "did:testName",
            "type":"skillCredential",
            "studentName": "testName",
            "courseName": "testCourse",
            "instituteName": "testInstitute",
            "issuedOn": "testDate",
            "skill": "testSkill"
        }
    },
    "credentialSchemaId": "did:schema:6962f014-e007-4ed3-87f9-552afce8c28e",
    "credentialSchemaVersion": "1.0.0",
    "tags": [
        "skill"
    ]
}"""
}
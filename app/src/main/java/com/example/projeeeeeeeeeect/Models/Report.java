package com.example.projeeeeeeeeeect.Models;

import com.example.projeeeeeeeeeect.Models.StatusType;
import com.google.gson.annotations.SerializedName;

public class Report {
    @SerializedName("id")
    int id;

    @SerializedName("user_id")
    int userId;

    @SerializedName("date_reported")
    String dateReported;

    @SerializedName("description")
    String description;

    @SerializedName("date_of_incident")
    String dateOfIncident;

    @SerializedName("location")
    String location;

    @SerializedName("incident_type_id")
    int incidentTypeId;

    @SerializedName("status_id")
    int statusId;

    // Use @SerializedName because the JSON key has a capital letter
    @SerializedName("IncidentType")
    IncidentType incidentType;

    @SerializedName("StatusType")
    StatusType statusType;
}

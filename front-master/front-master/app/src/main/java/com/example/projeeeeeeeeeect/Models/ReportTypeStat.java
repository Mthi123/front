package com.example.projeeeeeeeeeect.Models;

import com.google.gson.annotations.SerializedName;

public class ReportTypeStat {
    @SerializedName("incident_type_id")
    private int incidentTypeId;

    @SerializedName("incident_type")
    private String incidentType;

    @SerializedName("count")
    private int count;

    @SerializedName("IncidentType.id")
    private int nestedId;

    @SerializedName("IncidentType.name")
    private String nestedName;

    // Getters
    public int getIncidentTypeId() { return incidentTypeId; }
    public String getIncidentType() { return incidentType; }
    public int getCount() { return count; }
    public int getNestedId() { return nestedId; }
    public String getNestedName() { return nestedName; }
}
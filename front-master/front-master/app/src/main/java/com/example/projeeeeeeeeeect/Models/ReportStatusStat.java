package com.example.projeeeeeeeeeect.Models;

import com.google.gson.annotations.SerializedName;

public class ReportStatusStat {
    @SerializedName("status_id")
    private int statusId;

    @SerializedName("status_name")
    private String statusName;

    @SerializedName("count")
    private int count;

    @SerializedName("StatusType.id")
    private int nestedId;

    @SerializedName("StatusType.name")
    private String nestedName;

    // Getters (REQUIRED)
    public int getStatusId() { return statusId; }
    public String getStatusName() { return statusName; }
    public int getCount() { return count; }
    public int getNestedId() { return nestedId; }
    public String getNestedName() { return nestedName; }
}
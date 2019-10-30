package com.michael.data_source.model

import android.net.Uri

interface ResourceSummary {
    val id: Int
    val category: String
    val url: String

    fun getLastId(): Int {
        val urlList = url.split("/")
        if(url.endsWith("/")) {
            return urlList[urlList.size - 2].toInt()
        }
        return urlList.last().toInt()
    }
}

data class ApiResource(
    override val category: String,
    override val id: Int,
    override val url: String
) : ResourceSummary

data class NamedApiResource(
    val name: String,
    override val category: String,
    override val id: Int,
    override val url: String
) : ResourceSummary

private interface ResourceSummaryList<out T : ResourceSummary> {
    val count: Int
    val next: String?
    val previous: String?
    val results: List<T>
}

data class ApiResourceList(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<ApiResource>
) : ResourceSummaryList<ApiResource>

data class NamedApiResourceList(
    override val count: Int,
    override val next: String?,
    override val previous: String?,
    override val results: List<NamedApiResource>
) : ResourceSummaryList<NamedApiResource>
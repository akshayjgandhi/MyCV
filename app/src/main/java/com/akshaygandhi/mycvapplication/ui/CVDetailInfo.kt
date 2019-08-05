package com.akshaygandhi.mycvapplication.ui

import com.akshaygandhi.mycvapplication.data.model.CVDataModel
import com.akshaygandhi.mycvapplication.utils.formatDate

data class CVDetailInfo(val cvDataModel: CVDataModel) {

    var educationDetails: String = ""
    var workDetails: String = ""
    var skillsDetails: String = ""
    var profileDetail: String = ""
    lateinit var summary: String
    lateinit var website: String
    lateinit var email: String
    lateinit var label: String
    lateinit var name: String

    fun apiToUiMapper(): CVDetailInfo {
        val user = cvDataModel.basics

        name = user.name
        label = user.label
        email = user.email
        website = user.website
        summary = user.summary

        for (profile in user.profiles) {
            val tempProfile = """
               ${profile.network}
               ${profile.url}
               """.trimIndent()
            profileDetail += tempProfile
        }


        for (skills in cvDataModel.skills) {
            skillsDetails += skills.name

            for (keywords in skills.keywords) {
                val tempKeywords = """

                - $keywords
               """.trimIndent()
                skillsDetails += tempKeywords
            }
            skillsDetails += "\n"
        }

        for (work in cvDataModel.work) {
            workDetails += """
               ${work.position}
               ${work.company}
               ${work.website}
               """.trimIndent()
            if (work.startDate.isEmpty().not() && work.endDate.isEmpty().not()) {
                workDetails += "\n" + work.startDate.formatDate() + " - " + work.endDate.formatDate() +"\n"
            }

            workDetails += work.summary

            for (highlights in work.highlights) {
                val tempHighlights = """
                - $highlights
               """.trimIndent()
                workDetails += tempHighlights
            }

        }
        for (education in cvDataModel.education) {
            educationDetails += """
               ${education.institution}
               ${education.startDate.formatDate() + " - " + education.endDate.formatDate()}
               ${education.area}
               ${education.studyType}
               """.trimIndent()
        }

        return this
    }
}
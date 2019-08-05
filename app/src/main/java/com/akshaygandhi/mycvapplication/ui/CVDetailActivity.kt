package com.akshaygandhi.mycvapplication.ui

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.akshaygandhi.mycvapplication.R
import com.akshaygandhi.mycvapplication.app.BaseActivity
import kotlinx.android.synthetic.main.activity_cvdetail.*
import javax.inject.Inject

class CVDetailActivity : BaseActivity<CVDetailViewModel>() {

    private val userId: String = "akshaygandhiglobant"

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun getAssociatedViewModel(): CVDetailViewModel {
        return ViewModelProviders.of(this, viewModelFactory).get(CVDetailViewModelImpl::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cvdetail)

        val cvDetailInfoObserver = Observer<CVDetailInfo> { data ->
            if (data != null) {
                setCVDetails(data)
                cv_detail.visibility = View.VISIBLE
            } else {
                showError()
            }
        }

        viewModel.getCVDetail(userId).observe(this, cvDetailInfoObserver)
    }

    fun showError() {
        cv_detail.visibility = View.GONE
        Toast.makeText(this, getString(R.string.try_again), Toast.LENGTH_LONG).show()
    }

    fun setCVDetails(data: CVDetailInfo) {
        name.text = data.name
        label.text = data.label
        website.text = data.website
        email.text = data.email
        network_profile.text = data.profileDetail
        summary_title.visibility = View.VISIBLE
        summary.text = data.summary
        skills_title.visibility = View.VISIBLE
        skills.text = data.skillsDetails
        work_title.visibility = View.VISIBLE
        work.text = data.workDetails
        education_title.visibility = View.VISIBLE
        education.text = data.educationDetails
    }
}

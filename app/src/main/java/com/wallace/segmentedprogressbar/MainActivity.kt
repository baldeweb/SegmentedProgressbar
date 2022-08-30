package com.wallace.segmentedprogressbar

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.wallace.segmentedprogressbar.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initSegmentedProgressBar()
    }

    private fun initSegmentedProgressBar() {
        binding.segmentedProgressbar.setContainerColor(ContextCompat.getColor(this, R.color.teal_700))
        binding.segmentedProgressbar.setFillColor(ContextCompat.getColor(this, R.color.purple_200))
        binding.segmentedProgressbar.setSegmentGapWidth(40)
        binding.segmentedProgressbar.setCornerRadius(8)
        binding.segmentedProgressbar.setSegmentCount(4)
        binding.segmentedProgressbar.setCompletedSegments(1)
    }
}
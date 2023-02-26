package github.owlmail.lint.rules

import com.android.tools.lint.client.api.IssueRegistry
import com.android.tools.lint.client.api.Vendor
import com.android.tools.lint.detector.api.CURRENT_API
import github.owlmail.lint.rules.detectors.AndroidLogDetector
import github.owlmail.lint.rules.detectors.PrintlnDetector

@Suppress("UnstableApiUsage")
class OwlMailLintIssueRegistry : IssueRegistry() {
    override val issues = listOf(
        AndroidLogDetector.ISSUE,
        PrintlnDetector.ISSUE,
    )

    override val api = CURRENT_API

    override val vendor = Vendor(LintConstants.Vendor)
}

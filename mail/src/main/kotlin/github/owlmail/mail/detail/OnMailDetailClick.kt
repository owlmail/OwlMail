package github.owlmail.mail.detail

interface OnMailDetailClick {
    operator fun invoke(fileName: String?, part: String?, messageId: String?)
}

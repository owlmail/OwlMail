package github.owlmail.mail.inbox

interface OnMailClick {
    operator fun invoke(conversationUID: String?)
}

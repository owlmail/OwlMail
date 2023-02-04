package github.owlmail.mail.detail.model

data class ConvDetails(val cid: String) {
    fun mapToDetailRequest(): MailDetailRequest {
        return MailDetailRequest(
            MailDetailRequest.Body(
                MailDetailRequest.Body.SearchConvRequest(
                    cid = cid,
                    jsns = "urn:zimbraMail",
                    offset = 0,
                    limit = 10,
                    recip = "2", // 0=sender,1=receiver,2=both
                    fetch = "all",
                    html = 1,
                    needExp = 1,
                    max = 250000
                )
            )
        )
    }
}

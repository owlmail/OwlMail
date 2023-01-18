package github.owlmail.mail.detail

data class ConvDetails(val cid: String) {
    fun mapToDetailRequest(): MailDetailRequest {
        return MailDetailRequest(
            MailDetailRequest.Body(
                MailDetailRequest.Body.SearchConvRequest(
                    cid = cid,
                    jsns = "urn:zimbraMail",
                    offset = 0,
                    limit = 10,
//                    recip = "2",
                    fetch = "1"
                )
            )
        )
    }
}
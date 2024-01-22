client.test("Request executed successfully", () => {
    const validateExpression = !!response.status.toString().match(/20\d/)
    client.assert(validateExpression, "Response status is not 2xx")
})

client.test("Response body is array", () => {
    client.assert(Array.isArray(response.body), "Response body is not array")
    client.assert(response.body.length > 0, "Response body has no data")
})

client.test("Response data has board schema", () => {
    const board = response.body[0]
    client.assert(
        board.hasOwnProperty('id') &&
        board.hasOwnProperty('title') &&
        board.hasOwnProperty('content') &&
        board.hasOwnProperty('viewCnt') &&
        board.hasOwnProperty('likeCnt')
    )
})
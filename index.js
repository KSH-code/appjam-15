const app = require('easily-handle-error')(require('express')())

app.get('/', (req, res) => {
    res.json({})
})

app.use((err, req, res, next) => {
    console.error(err)
    res.status(500).end()
})

module.exports = app.listen(7001, () => {
    console.log('start')
})
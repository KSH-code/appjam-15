const request = require('request')
const { expect } = require('chai')
const app = require('../')
describe('테스트', () => {
    it('정상적으로 연결 되나?', done => {
        request.get('http://localhost:7001', {}, (err, res) => {
            expect(res.statusCode).to.equal(200)
            done()
        })
    })
})
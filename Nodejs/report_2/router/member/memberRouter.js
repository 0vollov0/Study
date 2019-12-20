var express = require('express')
var router = express.Router()

router.get('/loginForm',function(req,res){
    res.render('member/loginForm.ejs')
})

router.get('/joinForm',function(req,res){
    res.render('member/joinForm.ejs')
})

module.exports = router;
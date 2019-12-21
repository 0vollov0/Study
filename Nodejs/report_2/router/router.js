var express = require('express')
var router = express.Router()
var member = require('./member/memberRouter')
var session = require('express-session')

router.use(session({
    resave: false,
    saveUninitialized: false,
    secret: 'secret code',
    cookie: {
        httpOnly: true,
        secure: false,
    },
}));

router.get('/',function(req,res){
    res.render('index.ejs',{ 'mb_id' : req.session.mb_id})
})

router.use('/member',member)

module.exports = router;


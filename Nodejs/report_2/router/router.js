var express = require('express')
var router = express.Router()
var member = require('./member/memberRouter')

router.get('/',function(req,res){
    res.render('index.ejs')
})

router.use('/member',member)

module.exports = router;


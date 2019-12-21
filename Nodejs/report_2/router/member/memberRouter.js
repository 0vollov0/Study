var express = require('express')
var router = express.Router()
var mysql = require('mysql')
const crypto = require('crypto')

var connection = mysql.createConnection({
    host    :   'localhost',
    port    :   3306,
    user    :   'root',
    password:   'root',
    database:   'jsman'
});

connection.connect();

router.get('/joinForm',function(req,res){
    sessionCheck(req,res);

    res.render('member/joinForm.ejs')
})

router.get('/loginForm',function(req,res){
    sessionCheck(req,res);

    res.render('member/loginForm.ejs')
})

router.get('/logout',function(req,res){
    req.session.destroy();
    res.redirect('/');
})


router.post('/join',function(req,res){
    sessionCheck(req,res);

    var body = req.body;
    var mb_id = body.mb_id;
    var mb_pw = body.mb_pw;
    var mb_email = body.mb_email;

    mb_pw = crypto.createHash('sha512').update(mb_pw).digest('hex'); 

    var query_params = {'mb_id' : mb_id,'mb_pw' : mb_pw,'mb_email' : mb_email}

    var query = connection.query('INSERT INTO member SET ?', query_params, function (error, results, fields) {
        var result;
        if (error) result = { 'result' : 1 , 'message' : '회원가입 실패' , 'error' : error}
        else result = { 'result' : 0 , 'message' : '회원가입 성공'}
        res.json(result)
    });
})

router.post('/login',function(req,res){
    sessionCheck(req,res);

    var body = req.body;
    var mb_id = body.mb_id;
    var mb_pw = body.mb_pw;

    mb_pw = crypto.createHash('sha512').update(mb_pw).digest('hex'); 


    var query = connection.query('SELECT * FROM member WHERE mb_id =? AND mb_pw = ?',[mb_id,mb_pw] , function (error, results, fields) {
        var result;
        if (error) result = { 'result' : 1 , 'message' : '로그인 실패' , 'error' : error}
        if(results[0]){
            if(req.session.mb_id === undefined) req.session.mb_id = mb_id;
            result = { 'result' : 0 , 'message' : '로그인 성공'}
        }else{
            result = { 'result' : 2 , 'message' : '로그인 실패'}
        } 

        res.json(result)
    });
})

function sessionCheck(req,res){
    if(req.session.mb_id  != null && req.session.mb_id.length > 0) res.redirect('/');
}

module.exports = router;

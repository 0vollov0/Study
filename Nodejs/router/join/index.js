var express = require('express')
var app = express()
var router = express.Router();
var path = require('path')//상대경로
var mysql = require('mysql')

var connection = mysql.createConnection({
    host     : 'localhost',
    port     : 3306,
    user     : 'root',
    password : 'root',
    database : 'jsman'
});

connection.connect();

router.get('/',function(req,res){
    res.sendFile(path.join(__dirname,'../../public/join.html'))
});

router.post('/',function(req,res){
    var body = req.body;
    var email = body.email;
    var name = body.name;
    var pw = body.password;

    var sql = {email : email, name : name, pw : pw};

    connection.query('insert into user set ?', sql , function(err,rows){
        console.log(rows);
        if(err) throw err;
        else res.render('welcome.ejs',{'name' : name, 'id' : rows.insertId})
    })
});


module.exports = router;
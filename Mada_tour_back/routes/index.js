var express = require('express');
var router = express.Router();
const utilisateursRoute = require('./utilisateursRoute');
/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.use("/utilisateur",utilisateursRoute);

module.exports = router;

var express = require('express');
var router = express.Router();
const utilisateursRoute = require('./utilisateursRoute');
const activiteRoute = require('./activiteRoute');
const apiRouter = require('./router');

/* GET home page. */
router.get('/', function(req, res, next) {
  res.render('index', { title: 'Express' });
});

router.use("/utilisateur",utilisateursRoute);
router.use("/activite",activiteRoute);
//--router 
router.use("",apiRouter)
module.exports = router;

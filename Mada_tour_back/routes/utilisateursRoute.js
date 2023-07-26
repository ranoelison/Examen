var express = require('express');
var router = express.Router();
var utilisateursController = require('../applications/controllers/utilisateursController.js');

const path = require('path');


/*
 * Routes for   utilisateurs 
 */

router.post('/login', utilisateursController.login);
router.post('/inscription', utilisateursController.signin);

module.exports = router;
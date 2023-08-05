var express = require('express');
var router = express.Router();
var activiteController = require('../applications/controllers/activiteController.js');
const path = require('path');


/*
 * Routes for   Activite & Avis
 */

router.post('/addActivite', activiteController.addActivite);

router.get('/ficheActivite/:activite_id',activiteController.getActiviteById);
router.get('/listeAvisActivite/:activite_id',activiteController.getListAvisByActivite);
router.post('/addAvis', activiteController.addAvis);


module.exports = router;
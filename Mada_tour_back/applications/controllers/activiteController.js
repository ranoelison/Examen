const activiteModel = require('../models/Activite.js');
const Activite = require('../classes/Activite.js');
const Avis = require('../classes/Avis.js');

const sha1 = require('sha1');
const { last } = require('lodash');
const { now } = require('lodash');

/**
 * activiteController.js
 *
 * @description :: Server-side logic for managing  activite & avis.
 * 
 */
module.exports = {
    getListAvisByActivite : async function(req,res){
        try {
           
            const activite_id = req.query.activite_id;
            // || req.body.activite_id;

            const avis = await Avis.listAvisByActivite(activite_id);
            const response = {
                status: "200",
                message: "Liste des avis pour l'activité",
                data: avis
            };
            res.json(response);
            res.status(200);
        } catch (error) {
            console.error(error);
            const body = {
                status: "500",
                message: error.message,
                data: ""
            };
            res.json(body);
            res.status(500);
        }
    },
    addAvis : async function(req,res){
        const {utilisateur_id,activite_id,note,contenu} = req.body
        try{
            const newAvis = new Avis(utilisateur_id,activite_id,note,contenu);
           
            newAvis.insert().then(resp => {
             
                const response ={
                    status:"200",
                    message:"Ajout OK",
                    data:  newAvis
                };
                res.json(response);
                res.status(200);
            }).catch(error => {
                console.log(error);
                const body = {
                    status:"500",
                    message:error,
                    data : error
                };
                console.log(body);
              
                res.json(body);
                res.status(500);
              
            });
            
        }catch (error) {
            const body = {
                status:"500",
                message:error,
                data : ""
            };
            console.log(body);
          
            res.json(body);
            res.status(500);
        }
    },
    addActivite : async function(req,res){
        const {
                nom,type_activite,region,images_url,description,
                tarifA,tarifE,horaires,dayOff
            } = req.body
        try{
            const newActivite = new Activite(
                                                nom,type_activite,region,images_url,
                                                description,tarifA,tarifE,horaires,dayOff
                                            );
            newActivite.mode = "update";
            newActivite.insert().then(resp => {
               
                const response ={
                    status:"200",
                    message:"OK ",
                    data:  {
                        activite: newActivite
                    }
                };
                res.json(response);
                res.status(200);
            }).catch(error => {
                console.log(error);
                const body = {
                    status:"500",
                    message:error,
                    data : error
                };
                console.log(body);
              
                res.json(body);
                res.status(500);
              
            });
            
        }catch (error) {
            const body = {
                status:"500",
                message:error,
                data : ""
            };
            console.log(body);
          
            res.json(body);
            res.status(500);
        }
    },
};
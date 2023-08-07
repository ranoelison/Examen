const UtilisateursModel = require('../models/utilisateursModel.js');


const utilisateur = require('../classes/Utilisateurs.js');


const sha1 = require('sha1');
const { last } = require('lodash');
const { now } = require('lodash');
const Utilisateurs = require('../classes/Utilisateurs.js');
/**
 * utilisateursController.js
 *
 * @description :: Server-side logic for managing  utilisateur.
 * 
 */
module.exports = {
    
    login: async function (req, res) {
        const {mail, password} = req.body;
        try {
            const utilisateur =  await UtilisateursModel.findOne({
                mail : mail,
                password : sha1(password)
            });
            if(!utilisateur){
                const body = {
                    status:"500",
                    message : "Erreur d'authentification !",
                    data : ""
                };
                console.log(body);
                res.json(body);
                res.status(500);
            } else {
                const body = {
                    status:"200",
                    message : "Authentification OK!",
                    data : {
                        user : utilisateur,
                        token : utilisateur._id+"_"+now().toString()
                    }
                };
                res.json(body);
                res.status(200);
            }
        } catch (error) {
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
    signin:  async function (req, res) {
        const {nom,prenom, mail, password,confirmPassword,date_naissance} = req.body;
        try {
            if(password!=confirmPassword){
                throw "Erreur de confirmation mot de passe";
            }
           
            const utilisateur = new Utilisateurs();
            utilisateur.mode = "update";
            utilisateur.nom = nom;
            utilisateur.prenom = prenom;
            utilisateur.mail = mail;
            utilisateur.password = password;
            utilisateur.confirmPassword = confirmPassword;
            utilisateur.date_naissance = date_naissance;
            utilisateur.date_inscription =  new Date().toString();;
            //preparing mail
            //var subject = "Inscription sur MadaTour";
            //var mailContent = "Bienvenue "+nom+" "+prenom+"!";
           // mailContent = "".concat(mailContent,"<p>Votre compte MadaTour a été créé avec succès.</p>");
           // mailContent = "".concat(mailContent,"<p style='font-size:10px;'><b>MT</b></p><p style='font-size:8px;'><b>Rue L'Espoir,007Tana</b></p>");
          
           utilisateur.insert().then(resp => {
                //mailHelper.sendMail(receiver.mail,subject,mailContent);
                const token = resp._id+"_"+now().toString();
                const response ={
                    status:"200",
                    message:"OK ",
                    data:  {
                        user : utilisateur,
                        token : token
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

        } catch (error) {
            const body = {
                status:"500",
                message:error,
                data : ""
            };
            console.log(body);
          
            res.json(body);
            res.status(500);
        }
    }
};

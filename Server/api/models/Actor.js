/**
 * Actor.js
 *
 * @description :: A model definition.  Represents a database table/collection/etc.
 * @docs        :: https://sailsjs.com/docs/concepts/models-and-orm/models
 */

module.exports = {

  attributes: {

    nombres:{
      type:"string"
    },
    apellidos:{
      type:"string"
    },

    fechaNacimiento:{
      type:"number"
    },
    numeroPeliculas:{
      collection:"Peliculas",
      via:"entrenadorId"
    },

    retirado:{
      type:"Int"
    }

  },

};


function setConfig(config) {
       config.color = "#F8E530";
       config.size = 120;
       config.increaseSize = function() {
          config.size = config.size + 70;
       };
}

try { module.exports =  main_function; }
catch(err) { };

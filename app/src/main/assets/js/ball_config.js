function main_function(activity) {
    var counter = 0
    function behavior1(ctx) {
        ctx.segmentId = "segment1";
        ctx.listen = true;
        ctx.offTime = true;
        ctx.handVideo = true;
        ctx.tempo = activity.config.tempo1;
        activity.segments.segment1.expectation.successThreshold = 0.77;
       
        ctx.onFailure = function() {
            return behavior1;
        };
        ctx.onSuccess = function() {
            return behavior2;
        };
    }


    function behavior2(ctx) {
        ctx.segmentId = "segment2";
        ctx.listen = true;
        ctx.offTime = true;
        ctx.handVideo = true;
        ctx.tempo = activity.config.tempo2;
        activity.segments.segment1.expectation.successThreshold = 0.77;
       
        ctx.onFailure = function() {
            return Task2;
        };
    }

    return Task1;
}

try { module.exports =  main_function; }
catch(err) { };

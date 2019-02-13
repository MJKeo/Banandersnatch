var spot = 0;
var playerName;
var response = "";
var firstTime = true;
var hasBanana = false;

function update() {
    if (document.getElementById("response").value.toUpperCase().localeCompare("")) {
        if (playerName == null) {
            if (!document.getElementById("response").value.toUpperCase().localeCompare("APE")) {
                playerName = "Marc";
            } else if (!document.getElementById("response").value.toUpperCase().localeCompare("GIVE MIKE AN INTERNSHIP")) {
                console.log("correct");
                kingEnding();
                return;
            } else {
                playerName = document.getElementById("response").value;
            }
            document.getElementById("mainTitleLabel").innerText = ("BANANDERSNATCH - " + playerName);
            document.getElementById("response").value = "";
            spot++;
            firstEvent();
        } else {
            response = document.getElementById("response").value;
            document.getElementById("response").value = "";
        }
        console.log(response)
        if (spot == 1 && !firstTime) {
            if (!response.localeCompare("run")) {
                gameOver("You try and outrun the creature, but you make too much noise and alert the creature who chases you down and captures you.");
            } else if (!response.localeCompare("turn around")) {
                spot += 1;
                secondEvent();
            } else if (!response.localeCompare("climb tree")) {
                spot += 100;
                secondEventAlt();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 2) {
            if (!response.localeCompare("run")) {
                gameOver("You try and outrun the creature, but you the APE chases you down and captures you.");
            } else if (!response.localeCompare("fight")) {
                spot += 1;
                thirdEvent();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 101) {
            if (!response.localeCompare("take banana")) {
                spot = 123123;
                getBanana();
            } else if (!response.localeCompare("stay quiet")) {
                spot += 1;
                thirdEventAlt();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 3) {
            if (!response.localeCompare("follow")) {
                spot = 50;
                secondHalfIntro();
            } else if (!response.localeCompare("don't follow")) {
                spot++;
                thirdEventSneak();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 4) {
            var num = parseInt(response);
            var answer = Math.floor((Math.random() * 10) + 1);
            if (num == answer) {
                escapeEnding();
            } else {
                gameOver("The APE's keen hearing detects your escape and is offended, he hunts you down and captures you");
            }
        } else if (spot == 123123) {
            if (!response.localeCompare("continue")) {
                spot = 102;
                thirdEventAlt();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 102) {
            if (!response.localeCompare("run")) {
                gameOver("You try and outrun the creature, but you the APE chases you down and captures you.");
            } else if (!response.localeCompare("fight")) {
                spot = 3;
                thirdEvent();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 50) {
            if (!response.localeCompare("left")) {
                spot += 1;
                lookLeft();
            } else if (!response.localeCompare("right")) {
                spot += 2;
                lookRight();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 51 || spot == 52) {
            if (!response.localeCompare("continue")) {
                spot = 53;
                meetKing();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 53) {
            if (!response.localeCompare("bow")) {
                spot = 54;
                fightChampionApe();
            } else if (!response.localeCompare("try to communicate")) {
                gameOver("You make APE noise in an attempt to communicate, but the king speaks perfect english and is greatly offended, you're sentenced to life in prison");
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 54) {
            if (!response.localeCompare("continue")) {
                spot = 55;
                actualFight();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 55) {
            if (!response.localeCompare("rock")) {
                gameOver("The ape chose paper, for failing the challenge you are sentenced to life in prison");
            } else if (!response.localeCompare("paper")) {
                spot = 56;
                winFight();
            } else if (!response.localeCompare("scissors")) {
                gameOver("The ape chose rock, for failing the challenge you are sentenced to life in prison");
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 56) {
            if (!response.localeCompare("continue")) {
                spot = 57;
                kingOffer();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 57) {
            if (!response.localeCompare("accept")) {
                championEnding();
            } else if (!response.localeCompare("challenge king ape")) {
                spot = 58;
                challengeKing();
            } else if (!response.localeCompare("decline")) {
                gameOver("The King APE is offended by your refusal, you have been sentenced to life in prison");
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 58) {
            if (!response.localeCompare("rock")) {
                gameOver("The King APE chose paper, you are sentenced to life in prison");
            } else if (!response.localeCompare("paper")) {
                gameOver("The King APE chose scissors, you are sentenced to life in prison");
            } else if (!response.localeCompare("scissors")) {
                gameOver("The King APE chose rock, you are sentenced to life in prison");
            } else if (!response.localeCompare("banana") && hasBanana) {
                spot = 1000;
                beatKing();
            } else {
                document.getElementById("response").value = "Please enter a valid input";
            }
        } else if (spot == 1000) {
            if (!response.localeCompare("continue")) {
                kingEnding();
            } else {
                responseField.setText("Please enter a valid input");
            }
        } else if (firstTime) {
            firstTime = false;
        }
    } else {
        document.getElementById("response").value = "Please enter a valid input";
    }
}

function firstEvent() {
        document.getElementById("image").src="http://blogs.discovermagazine.com/d-brief/files/2017/08/shutterstock_244338682.jpg";
        document.getElementById("prompt").innerText = "You're alone on an expedition in the Amazon Jungle and you hear a rustle behind you";
        document.getElementById("prompt2").innerText = "What do you do? (run, turn around, climb tree)";
}

function secondEvent() {
        document.getElementById("image").src="https://c1.staticflickr.com/5/4075/4740022283_f1f2329b53_b.jpg";
        document.getElementById("prompt").innerText = "You turn around and see an enourmous APE staring down at you";
        document.getElementById("prompt2").innerText = "What do you do? (run, fight)";
}

function secondEventAlt() {
        document.getElementById("image").src="https://images.fineartamerica.com/images/artworkimages/mediumlarge/2/you-big-ape-robert-frank-gabriel.jpg";
        document.getElementById("prompt").innerText = "You climb the tree, you see a massive ape below. There are also bananas in your tree";
        document.getElementById("prompt2").innerText = "What do you do? (take banana, stay quiet)";
}

function thirdEvent() {
        document.getElementById("image").src="https://www.sciencedaily.com/images/2007/08/070802091437_1_900x600.jpg";
        document.getElementById("prompt").innerText = "The APE respects your fighting spirit and motions for you to follow";
        document.getElementById("prompt2").innerText = "What do you do? (follow, don't follow)";
}

function getBanana() {
        hasBanana = true;
        document.getElementById("image").src="https://images-na.ssl-images-amazon.com/images/I/71gI-IUNUkL._SY355_.jpg";
        document.getElementById("prompt").innerText = "You now have a banana";
        document.getElementById("prompt2").innerText = "Type 'continue' to continue";
}

function thirdEventAlt() {
        document.getElementById("image").src="https://c1.staticflickr.com/5/4075/4740022283_f1f2329b53_b.jpg";
        document.getElementById("prompt").innerText = "You climb down once you think it's safe, but turn to see an enourmous APE staring";
        document.getElementById("prompt2").innerText = "down at you. What do you do? (run, fight)";
}

function thirdEventSneak() {
    document.getElementById("prompt").innerText = "You try to sneak away while the APE's not looking";
    document.getElementById("prompt2").innerText = "Pick a number between 1 and 10 [inclusive]";
}


// SECOND HALF OF BANANDERSNATCH

function secondHalfIntro() {
    document.getElementById("image").src="https://www.ancient-origins.net/sites/default/files/field/image/lost-city-of-gold.JPG";
        document.getElementById("prompt").innerText = "You follow the APE. He brings you to the lost city of APElantis. You take a look";
        document.getElementById("prompt2").innerText = "around. Which way do you look? (left, right)";
}

function lookLeft() {
    document.getElementById("image").src="http://rotocasted.com/static/mediafiles/tmpirUbaL.jpg.580x580_q85.jpg";
        document.getElementById("prompt").innerText = "You look left and see a 50ft gold statue of an ape wearing a crown. Looks like some";
        document.getElementById("prompt2").innerText = "sort of king. Type 'continue' to continue";
}

function lookRight() {
    document.getElementById("image").src="http://theculturalgutter.com/wp-content/uploads/2012/07/pota-magno.jpg";
        document.getElementById("prompt").innerText = "You look right and see thousands of APEs, marketplaces, and homes. It contains";
        document.getElementById("prompt2").innerText = "technology you can't even fathom. Type 'continue' to continue";
}

function meetKing() {
    document.getElementById("image").src="https://res.cloudinary.com/teepublic/image/private/s--S3Net-__--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1534188776/production/designs/3016141_0.jpg";
        document.getElementById("prompt").innerText = "You are brought into a palace of sorts. In it you are met with the King APE";
        document.getElementById("prompt2").innerText = "What do you do? (bow, try to communicate)";
}

function fightChampionApe() {
    document.getElementById("image").src="https://res.cloudinary.com/teepublic/image/private/s--S3Net-__--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1534188776/production/designs/3016141_0.jpg";
        document.getElementById("prompt").innerText = "You bow before the King APE, who declares that you must prove yourself by fighting";
        document.getElementById("prompt2").innerText = "the APE Champion. Type 'continue' to continue";
}

function actualFight() {
    document.getElementById("image").src="https://vignette.wikia.nocookie.net/planetoftheapes/images/f/fd/1396985361008-09-ss036-0180-v157-le1077.jpg/revision/latest?cb=20140409125015";
        document.getElementById("prompt").innerText = "The Champion APE comes out from the shadows, he is 15ft tall and RIPPED.";
        document.getElementById("prompt2").innerText = "Choose your weapon (rock, paper, scissors)";
}

function winFight() {
    document.getElementById("image").src="https://res.cloudinary.com/teepublic/image/private/s--5cPQH0NH--/t_Resized%20Artwork/c_fit,g_north_west,h_954,w_954/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_ffffff,e_outline:48/co_ffffff,e_outline:inner_fill:48/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_jpg,h_630,q_90,w_630/v1503238736/production/designs/1837630_0.jpg";
        document.getElementById("prompt").innerText = "Your paper beats the Champion APE's rock, you have won!";
        document.getElementById("prompt2").innerText = "Type 'continue' to continue";
}

function kingOffer() {
    document.getElementById("image").src="https://res.cloudinary.com/teepublic/image/private/s--S3Net-__--/t_Preview/b_rgb:191919,c_limit,f_jpg,h_630,q_90,w_630/v1534188776/production/designs/3016141_0.jpg";
        document.getElementById("prompt").innerText = "The King APE is impressed and offers to make you the new Champion APE";
        document.getElementById("prompt2").innerText = "What do you do? (accept, decline, challenge King APE)";
}

function challengeKing() {
    document.getElementById("image").src="https://f4.bcbits.com/img/0006163982_10.jpg";
        document.getElementById("prompt").innerText = "The King APE accepts your challenge";
        document.getElementById("prompt2").innerText = "Choose your weapon (rock, paper, scissors)";
}

function beatKing() {
        document.getElementById("image").src="https://f4.bcbits.com/img/0006163982_10.jpg";
        document.getElementById("prompt").innerText = "You take out the banana as the King APE throws scissors. All APEs are momentarily";
        document.getElementById("prompt2").innerText = "distracted as you switch your hand to rock, you win! Type 'continue' to continue";
}

function gameOver(message) {
    document.getElementById("image").src="https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/2T0t-6V/videoblocks-game-over-8-bit-funky-a-funky-colorful-4k-game-over-screen-animation-letters-falling-towards-the-center-8-bit-retro-style-red-and-yellow_hcqx9_kax_thumbnail-full06.png";
    document.getElementById("prompt").innerText = message;
    document.getElementById("prompt2").innerText = "Refresh the page to try again";
}

function escapeEnding() {
    document.getElementById("image").src="https://blog.radissonblu.com/wp-content/uploads/2014/11/escape-room.jpg";
    document.getElementById("prompt").innerText = "ENDING #1";
    document.getElementById("prompt2").innerText = "You successfully escaped the APE, but you feel as if you've missed a great adventure";
}

function championEnding() {
    document.getElementById("image").src="https://img.wondercostumes.com/imgzoom/babealonian-warrior-man-68630.jpg";
    document.getElementById("prompt").innerText = "ENDING #2";
    document.getElementById("prompt2").innerText = "Congrats you are now the Champion APE!!";
}

function kingEnding() {
    console.log("correct");
    document.getElementById("image").src="https://yt3.ggpht.com/a-/AAuE7mD5AkH2PbSMcp4Z8CJg0EM22PvjuPzvdrOA3Q=s900-mo-c-c0xffffffff-rj-k-no";
    document.getElementById("prompt").innerText = "ENDING #3";
    document.getElementById("prompt2").innerText = "You have become the King APE. You are now a God among APEs";
}
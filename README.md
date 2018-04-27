# SignTalk - A Sign Language Recognition System

<p>Many people with a hearing disability rely on sign language to communicate. However, only a fraction of the world population understands this form of communication. Sign Language Recognition (SLR) is a method that allows such people to communicate with the society. The goal of this project is to develop an American Sign Language (ASL) recognition system by using two separate Machine Learning models and comparing their accuracies. One model is trained using surface Electromyography (sEMG) signals from the user's forearm and the other is trained using RGB-D (RGB with depth dimension) data. Due to limited resources, this project is only targeting 10 letters of the English alphabet as the gestures for these letters are very distinct and can be done with only one hand. Following are the letters used.</p>

<p style="text-align: center;">
    A, B, D, G, I, L, N, P, V, W
</p>

<p>The data collection is done using two separate hardware. The sEMG data is collected using the XTREMIS Board, designed by WiSeR \cite{r3}. The RGB-D data is collected using the Tango Tablet via its Infra-Red (IR) sensors and Fish Eye camera. The two input sources are processed separately and the accuracy of each is compared.</p>
<p>Overall, the results of this project are expected to give an insight on advantages and disadvantages for the two input sources mentioned. The accuracy will be based on the number of correct predictions from the test data set.</p>

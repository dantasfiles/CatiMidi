Classifying MIDI files using machine learning for Cornell CS 4780: *Machine Learning.* Collaboration with Chee Yong Lee.

http://www.cs.cornell.edu/courses/cs4780/

The first step in our project is decoding the MIDI files into data that our learning algorithms could process. We chose MIDI files as our representation of music to classify because in a MIDI file, binary messages represent events such as switching instruments, playing notes, and beating on percussion instruments. We also chose the General MIDI specification, which is used by a majority of MIDI files. This format includes certain instruments and ensures that the decoder can decide which instrument is playing which note. Due to the elegant simplicity of the MIDI format, we could concentrate on designing our learning algorithms and proving the concept of musical classification.

We used the JavaSound package that is included in the Java Development Kit Version 1.3. The JavaSound library converts a Midi file into a list of Midi events in binary message format with their corresponding times of occurrence. For instance, a NOTE ON message (press down on the note with a certain volume) binary message would be paired with a time. Our decoder parsed through the list of binary messages, interpreted them, and translated them into a form that would represent the music for our learning algorithms.

We divide each piece of music into time periods of equal length. We then create the data for the learning algorithms as a sample separately for each time period. The learning algorithm then takes these samples and analyzes them. When we were designing our system, we were concerned that by analyzing each time period separately, it would not track change in the music over large periods of time. However, we were justified in our choices because the characteristics that place music into a certain category seemed occur throughout the piece of music. By setting the length of our time period wide enough, nearly each sample characterized the piece of music and there were still enough samples to classify the files.

For each time period, we chose the three categories, notes, instruments, and beats, to represent each piece of music. The notes category tracked which notes/frequencies were played most often in the music. We used a scaling equation of "score = time note is played * volume of note." This ensured that notes that were louder or played for longer periods of time counted more then short or soft notes as the loud notes would be heard more prominently by the listener and thus would better define the musical piece. We divided a piano keyboard into blocks of 4 notes, used the above scaling equation to generate results, and then normalized across the keyboard. In the first version of our decoding software, we characterized by the audible frequencies from 20 Hz to 20 kHz. However, we observed that most of the notes in the low range of the spectrum and were not occurring in a linear fashion. We learned that in a musical system, the low notes are closer together in frequency than the higher notes and that most of the music took place in the middle of the keyboard. We realized that a better classification would be made by recording blocks of notes rather than of frequencies. As seen by the graphic in the appendix, a compilation of the notes played in multiple songs, we achieved rather uniform results using this method. As is also indicated in the plot, we also noticed that no songs played the very high notes or the very low notes. Since our learning methods worked better with less inputs (or dimensions) per sample, we eliminated the blocks of notes at the top and bottom of the keyboard that were never played. Our final classification seemed to work well, to classify which notes were being played most frequently by the piece of music, for instance, whether a piece of music ranged over much of the keyboard, played only high notes, or played only in certain octaves.

We also tracked the instruments that were being played. We divided the musical instruments into families, such as pianos, guitars, etc.. The same system of scaling was used as for notes, factoring in the time the note was played and the volume of the note. We then normalized across the families. The learning algorithms could now use the type of instruments that the piece of music used to classify the music. For example, rock music may use mostly guitars, classical music may use mostly orchestral instruments or pianos, and jazz music may use mostly saxophones or other wind instruments.

Our last classification was beats. This was a simple number that characterized how many hits to percussive instruments occurred during the time period multiplied by a scaling constant. This enabled the learning algorithms to use the speed or tempo of the music in their classifications. With these these three categories for each of the time samples, each MIDI file could be transformed into samples (or points in dimensional space) that could be given to our learning algorithms for training, validation, or testing. 

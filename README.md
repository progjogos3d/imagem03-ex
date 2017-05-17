# Resolução dos exercícios da aula 3

Contém a resolução dos exercícios da aula de histogramas.

Há três funções importantes:
* histogram: Que calcula o histograma da imagem
* accumHistogram: Que gera o histograma acumulado
* drawHistogram: Que desenha o histograma passado por parâmetro

O exemplo gera histogramas de 3 imagens da pasta /gray:
* lizard.jpg: Uma imagem com bom contraste
* cars.jpg: Uma imagem acinzentada
* university.jpg: Imagem escura com pouca graduação de preto

É interessante observar especialmente os histogramas acumulados. Na imagem escura, a curva ficará posicionada no início,
na imagem acinzentada estará íngreme no meio. 

Já na imagem normal, a curva será menos íngreme. Uma imagem perfeitamente balanceada teria seria o quadrado dividido na 
diagonal exata, com uma metade preta outra branca.
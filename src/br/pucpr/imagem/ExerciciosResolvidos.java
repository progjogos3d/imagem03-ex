package br.pucpr.imagem;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Resolução dos exercícios sobre histogramas
 */
public class ExerciciosResolvidos {
    public static final File PATH = new File("/Users/vinigodoy/img");

    /**
     * Salva a imagem no disco.
     */
    private void salvar(BufferedImage img, String name) throws IOException {
        ImageIO.write(img, "jpg", new File(name + ".jpg"));
        System.out.printf("Salvo %s.png%n", name);
    }

    /**
     * Exercício 1a: Calcular o histograma da imagem
     *
     * O histograma é um array que contém, em cada índice, a quantidade de pixels encontrados na imagem que
     * contém o tom daquele índice. Por exemplo, o índice 0 do histograma terá a quantidade de pixels pretos (cor 0)
     * da imagem.
     */
    public int[] histogram(BufferedImage img) {
        //Criamos o histograma com um índice para cada tom de cinza
        int[] hist = new int[256];

        //Percorremos a imagem somando 1 a cada tom de cinza encontrado
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                //Obtem o tom de cinza do pixel (x,y)
                int tom = new Color(img.getRGB(x, y)).getRed();

                //Soma 1 no índice correspondente ao tom no histograma
                hist[tom] += 1;
            }
        }
        return hist;
    }


    /**
     * Exercicio 1b: Calculador o histograma acumulado
     *
     * O histograma acumulado de uma imagem contém no índice[0] a quantidade de pixel com o tom de cinza 0.
     * No índice [1] a quantidade de pixels com os tons de cinza 0 e 1.
     * No índice [2] a quantidade de pixels com os tons de cinza 0, 1 e 2. E assim por diante.
     * Dessa forma, o índice 255 conterá a quantidade de pixels presentes na imagem inteira.
     */
    public int[] accumHistogram(int[] histogram) {
        int[] accum = new int[histogram.length];

        //O primeiro índice do histograma normal e do acumulado são iguais
        accum[0] = histogram[0];

        //A partir do índice 1, soma o valor anterior ao atual
        for (int i = 1; i < histogram.length; i++) {
            accum[i] = histogram[i] + accum[i-1];
        }

        return accum;
    }

    /**
     * Exercício 2
     * Desenha o histograma passado por parametro numa imagem 512x600.
     */
    public BufferedImage drawHistogram(int[] histogram) {
        //Vamos procurar o maior valor do histograma, para que a barrinha dele tenha altura 600
        int max = 0;
        for (int value : histogram) {
            if (max < value) {
                max = value;
            }
        }

        //Calculamos a proporção.
        float prop = 600.0f / max;

        //Agora vamos desenhar o gráfico. Iremos criar 2 barrinhas verticais para cada indice do histograma.
        BufferedImage out = new BufferedImage(512, 600, BufferedImage.TYPE_INT_RGB);

        //Desenhamos a linha usando o objeto Graphics2D, como sugerido no enunciado
        Graphics2D g = out.createGraphics();
        for (int i = 0; i < 512; i++) {
            //Calculamos a altura da linha com base no histograma
            int idx = i / 2; //Indice do histograma sendo desenhado
            int h = 600 - (int) (histogram[idx] * prop); //Invertido pois a altura 0 é no topo da imagem

            //Desenhamos uma linha
            g.drawLine(i, h, i, 599);
        }
        //Boa prática: chamar dispose no objeto graphics
        g.dispose();
        return out;
    }

    public void run() throws IOException {
        //Gera os histogramas do lagarto
        BufferedImage img = ImageIO.read(new File(PATH, "gray/lizard.jpg"));
        int[] hist = histogram(img);
        int[] accum = accumHistogram(hist);
        salvar(drawHistogram(hist), "lizardHistogram");
        salvar(drawHistogram(accum), "lizardAccumHistogram");

        //Gera os histogramas do carro (imagem acinzentada)
        img = ImageIO.read(new File(PATH, "gray/cars.jpg"));
        hist = histogram(img);
        accum = accumHistogram(hist);
        salvar(drawHistogram(hist), "carsHistogram");
        salvar(drawHistogram(accum), "carsAccumHistogram");

        //Gera os histogramas da university (imagem escura)
        img = ImageIO.read(new File(PATH, "gray/university.jpg"));
        hist = histogram(img);
        accum = accumHistogram(hist);
        salvar(drawHistogram(hist), "universityHistogram");
        salvar(drawHistogram(accum), "universityAccumHistogram");
    }

    public static void main(String[] args) throws IOException {
        new ExerciciosResolvidos().run();
    }
}

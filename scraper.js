const puppeteer = require('puppeteer'); // vai abrir o site e pegar as imagens

(async () => {
  // URL do capítulo que você quer testar
  const chapterUrl = 'COLE_AQUI_A_URL_DO_CAPITULO';

  const browser = await puppeteer.launch({ headless: true });
  const page = await browser.newPage();
  await page.goto(chapterUrl);

  // Seleciona todas as imagens usando o seletor CSS
  const imageUrls = await page.$$eval('img.w-full.h-full', imgs =>
    imgs.map(img => img.src)
  );

  console.log(imageUrls); // imprime os links das imagens

  await browser.close();
})();

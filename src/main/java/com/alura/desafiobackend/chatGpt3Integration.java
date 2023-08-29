package com.alura.desafiobackend;


import com.theokanning.openai.completion.CompletionRequest;
import com.theokanning.openai.service.OpenAiService;


public class chatGpt3Integration {
    private static final String API_KEY = "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"; // Substitua pela sua chave de API GPT-3

    public static String gerarTextoDescritivo(String destinoNome) {
      String prompt = "Faça um resumo sobre " + destinoNome + " enfatizando o porquê deste lugar é incrível. Utilize uma linguagem informal e até 100 caracteres no máximo em cada parágrafo. Crie 2 parágrafos neste resumo.";

      OpenAiService openAiService = new OpenAiService(API_KEY);
      
      CompletionRequest completionRequest = CompletionRequest.builder()
        .model("gpt-3.5-turbo")
        .prompt(prompt)
        .maxTokens(200)
        .build();

      String response = openAiService.createCompletion(completionRequest).getChoices().get(0).getText();

      return response;
  }
}


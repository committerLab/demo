Feature: user's feature
  Scenario: récupérer un utilisateur
    Given appeler la abonne url
    When je donne l'id 1
    Then je récupère bien l'utilisateur

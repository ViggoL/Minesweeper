package minesweeper.Model;

import javafx.event.Event;
import javafx.scene.control.Alert;
import minesweeper.Controller.GridController;

    /**
     * code snippet from https://examples.javacodegeeks.com
     */
    public class TileEventException extends RuntimeException {

        public TileEventException() {

        }

        public TileEventException(String message) {
            super(message);
            Alert a;
            a = new Alert(Alert.AlertType.ERROR);
            a.setTitle("Serious Runtime Error");
            a.setContentText("A serious error has interrupted your game. \n"
                    + "Should the problems persist,\n"
                    + "please e-mail the Minesweeper team at support@minesweep.com");
            a.setOnCloseRequest(this::TerminateGame);
            a.setGraphic(GridController.imageViewButton);
            a.show();
        }

        public TileEventException(Throwable cause) {
            super(cause);
        }

        public TileEventException(String message, Throwable cause) {
            super(message, cause);

        }

        public TileEventException(String message, Throwable cause,
                boolean enableSuppression, boolean writeableStacktrace) {
            super(message, cause, enableSuppression, writeableStacktrace);
        }

        private void TerminateGame(Event event) {
            System.exit(666);
        }

    }
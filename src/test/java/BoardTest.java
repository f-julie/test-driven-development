import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BoardTest {
    private Board game;

    @BeforeEach
    void setUp() {
        game = new Board(8, 8);
    }

    @Test
    void insertPieceA() {
        Piece expected = Piece.A;

        game.insertPiece(expected, 0, 0);

        Piece actual = game.get(0, 0);
        assertEquals(expected, actual);
    }

    @Nested
    @DisplayName("Tests for move function")
    class move {

        @Test
        void moveSame() {
            assertFalse(game.move(0, 0, 0, 0));
        }

        @Test
        void startOutOfBounds() {
            assertFalse(game.move(9, 9, 8, 8));
            assertFalse(game.move(-1, -1, 8, 8));
        }

        @Test
        void endOutOfBounds() {
            assertFalse(game.move(0, 0, 8, 8));
            assertFalse(game.move(7, 7, -8, -8));
        }

        @Nested
        @DisplayName("Tests for Moving Piece A")
        class movePieceA {

            private Piece expected = Piece.A;

            @BeforeEach
            void setupPieceA() {
                game.insertPiece(expected, 0, 0);
            }

            @Nested
            @DisplayName("All valid moves for Piece A")
            class validMoves {

                @Test
                void validHorizontalMove() {
                    game.move(0, 0, 1, 0);
                    Piece actual = game.get(1, 0);

                    assertEquals(expected, actual);
                }

                @Test
                void validVerticalMove() {
                    game.move(0, 0, 0, 1);
                    Piece actual = game.get(0, 1);

                    assertEquals(expected, actual);
                }

            }

            @Test
            void invalidMoves() {
                assertFalse(game.move(0,0,1,1));
            }

            @Test
            void testCollision() {
                Piece collider = Piece.A;
                game.insertPiece(collider, 1, 0);

                game.move(0,0,1,0); // Collides on the right

                Piece actual = game.get(2,0);
                assertEquals(expected, actual);
            }

            @Test
            void testCollisionOffBoard() {
                game.insertPiece(expected, 7, 6);

                Piece collider = Piece.A;
                game.insertPiece(collider, 7,7);

                game.move(7,6,0,1);
                Piece actual = game.get(7,6);
                assertEquals(expected, actual);
            }

/*
            @Nested
            @DisplayName("All valid moves for Piece B")
            class validMoves {

                @Test
                void validHorizontalMove() {
                    game.move(0, 0, 1, 0);
                    Piece actual = game.get(1, 0);

                    assertEquals(expected, actual);
                }

                @Test
                void validVerticalMove() {
                    game.move(0, 0, 0, 1);
                    Piece actual = game.get(0, 1);

                    assertEquals(expected, actual);
                }

            }

            @Test
            void invalidMoves() {
                assertFalse(game.move(0,0,1,1));
            }

            @Test
            void testCollision() {
                Piece collider = Piece.A;
                game.insertPiece(collider, 1, 0);

                game.move(0,0,1,0); // Collides on the right

                Piece actual = game.get(2,0);
                assertEquals(expected, actual);
            }

 */
        }
    }
}
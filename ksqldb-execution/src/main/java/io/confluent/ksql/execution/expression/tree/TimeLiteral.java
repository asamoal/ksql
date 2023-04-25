/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.ksql.execution.expression.tree;

import static java.util.Objects.requireNonNull;

import com.google.errorprone.annotations.Immutable;
import io.confluent.ksql.parser.NodeLocation;
import java.sql.Time;
import java.util.Objects;
import java.util.Optional;

@Immutable
public class TimeLiteral extends Literal {

  private final long value;

  public TimeLiteral(final Time value) {
    this(Optional.empty(), value);
  }

  public TimeLiteral(final Optional<NodeLocation> location, final Time value) {
    super(location);
    this.value = requireNonNull(value, "value").getTime();
  }

  @Override
  public Time getValue() {
    return new Time(value);
  }

  @Override
  public <R, C> R accept(final ExpressionVisitor<R, C> visitor, final C context) {
    return visitor.visitTimeLiteral(this, context);
  }

  @Override
  public boolean equals(final Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    final TimeLiteral that = (TimeLiteral) o;
    return Objects.equals(value, that.value);
  }

  @Override
  public int hashCode() {
    return Long.hashCode(value);
  }
}
